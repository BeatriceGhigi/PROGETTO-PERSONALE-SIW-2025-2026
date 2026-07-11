import { useEffect, useState } from 'react'

const API_BASE = 'http://localhost:8080/api'

function App() {
  const [categorie, setCategorie] = useState([])
  const [brands, setBrands] = useState([])
  const [prodotti, setProdotti] = useState([])

  const [categoriaId, setCategoriaId] = useState('')
  const [brandId, setBrandId] = useState('')
  const [prezzoMin, setPrezzoMin] = useState('')
  const [prezzoMax, setPrezzoMax] = useState('')

  const [caricamento, setCaricamento] = useState(false)
  const [errore, setErrore] = useState(null)

  // Al primo caricamento, popola le select di categoria e brand
  useEffect(() => {
    fetch(`${API_BASE}/categorie`)
      .then((res) => res.json())
      .then(setCategorie)
      .catch(() => setErrore('Impossibile caricare le categorie.'))

    fetch(`${API_BASE}/brand`)
      .then((res) => res.json())
      .then(setBrands)
      .catch(() => setErrore('Impossibile caricare i brand.'))
  }, [])

  // Esegue la ricerca con i filtri attuali
  const cercaProdotti = () => {
    setCaricamento(true)
    setErrore(null)

    const params = new URLSearchParams()
    if (categoriaId) params.append('categoriaId', categoriaId)
    if (brandId) params.append('brandId', brandId)
    if (prezzoMin) params.append('prezzoMin', prezzoMin)
    if (prezzoMax) params.append('prezzoMax', prezzoMax)

    fetch(`${API_BASE}/prodotti?${params.toString()}`)
      .then((res) => {
        if (!res.ok) throw new Error('Errore nella richiesta')
        return res.json()
      })
      .then(setProdotti)
      .catch(() => setErrore('Impossibile caricare i prodotti.'))
      .finally(() => setCaricamento(false))
  }

  // Esegue una ricerca iniziale (senza filtri) al primo caricamento
  useEffect(() => {
    cercaProdotti()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  const resetFiltri = () => {
    setCategoriaId('')
    setBrandId('')
    setPrezzoMin('')
    setPrezzoMax('')
  }

  return (
    <div className="container">
      <h1>🌱 Ricerca Prodotti Vegani</h1>

      <div className="filtri">
        <div className="campo">
          <label htmlFor="categoria">Categoria</label>
          <select id="categoria" value={categoriaId} onChange={(e) => setCategoriaId(e.target.value)}>
            <option value="">Tutte</option>
            {categorie.map((c) => (
              <option key={c.id} value={c.id}>{c.nome}</option>
            ))}
          </select>
        </div>

        <div className="campo">
          <label htmlFor="brand">Brand</label>
          <select id="brand" value={brandId} onChange={(e) => setBrandId(e.target.value)}>
            <option value="">Tutti</option>
            {brands.map((b) => (
              <option key={b.id} value={b.id}>{b.nome}</option>
            ))}
          </select>
        </div>

        <div className="campo">
          <label htmlFor="prezzoMin">Prezzo min (€)</label>
          <input
            id="prezzoMin"
            type="number"
            min="0"
            step="0.01"
            value={prezzoMin}
            onChange={(e) => setPrezzoMin(e.target.value)}
          />
        </div>

        <div className="campo">
          <label htmlFor="prezzoMax">Prezzo max (€)</label>
          <input
            id="prezzoMax"
            type="number"
            min="0"
            step="0.01"
            value={prezzoMax}
            onChange={(e) => setPrezzoMax(e.target.value)}
          />
        </div>

        <div className="azioni">
          <button onClick={cercaProdotti}>Cerca</button>
          <button className="secondario" onClick={resetFiltri}>Reset</button>
        </div>
      </div>

      {caricamento && <p>Caricamento in corso...</p>}
      {errore && <p className="errore">{errore}</p>}

      {!caricamento && !errore && (
        <>
          <p className="risultati-count">{prodotti.length} prodotti trovati</p>
          <div className="griglia-prodotti">
            {prodotti.map((p) => (
              <div className="card-prodotto" key={p.id}>
                <h3>{p.nome}</h3>
                <p className="brand-nome">{p.brandNome} · {p.categoriaNome}</p>
                <p className="prezzo">{p.prezzo?.toFixed(2)} €</p>
                <p className="formato">{p.formato}</p>
                <div className="badge-container">
                  {p.vegan && <span className="badge">Vegan</span>}
                  {p.crueltyFree && <span className="badge">Cruelty Free</span>}
                </div>
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  )
}

export default App
