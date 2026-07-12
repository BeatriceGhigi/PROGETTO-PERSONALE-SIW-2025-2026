import { useEffect, useState } from 'react';
import type { Categoria, Brand, Prodotto, FiltriProdotto } from './types';
import { getCategorie, getBrand, cercaProdotti } from './services/prodottoService';
import FiltriProdottiForm from './components/FiltriProdotti';
import ListaProdotti from './components/ListaProdotti';
import './App.css';

function App() {
  const [categorie, setCategorie] = useState<Categoria[]>([]);
  const [brands, setBrands] = useState<Brand[]>([]);
  const [prodotti, setProdotti] = useState<Prodotto[]>([]);
  const [filtri, setFiltri] = useState<FiltriProdotto>({});

  const [caricamento, setCaricamento] = useState(false);
  const [errore, setErrore] = useState<string | null>(null);

  // Al primo caricamento: popola le select e mostra tutti i prodotti (nessun filtro)
  useEffect(() => {
    getCategorie().then(setCategorie).catch((err: Error) => setErrore(err.message));
    getBrand().then(setBrands).catch((err: Error) => setErrore(err.message));
    eseguiRicerca({});
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const eseguiRicerca = (filtriAttuali: FiltriProdotto) => {
    setCaricamento(true);
    setErrore(null);

    cercaProdotti(filtriAttuali)
      .then(setProdotti)
      .catch((err: Error) => setErrore(err.message))
      .finally(() => setCaricamento(false));
  };

  const handleCerca = () => {
    eseguiRicerca(filtri);
  };

  const handleReset = () => {
    setFiltri({});
    eseguiRicerca({});
  };

  return (
    <div className="container">
      <h1>🌱 Ricerca Prodotti Vegani</h1>

      <FiltriProdottiForm
        categorie={categorie}
        brands={brands}
        filtri={filtri}
        onCambiaFiltri={setFiltri}
        onCerca={handleCerca}
        onReset={handleReset}
      />

      {caricamento && <p>Caricamento in corso...</p>}
      {errore && <p className="errore">{errore}</p>}
      {!caricamento && !errore && <ListaProdotti prodotti={prodotti} />}
    </div>
  );
}

export default App;
