import type { Categoria, Brand, FiltriProdotto } from '../types';

interface FiltriProdottiProps {
  categorie: Categoria[];
  brands: Brand[];
  filtri: FiltriProdotto;
  onCambiaFiltri: (filtri: FiltriProdotto) => void;
  onCerca: () => void;
  onReset: () => void;
}

function FiltriProdotti({ categorie, brands, filtri, onCambiaFiltri, onCerca, onReset }: FiltriProdottiProps) {

  const aggiornaCampo = (campo: keyof FiltriProdotto, valore: string) => {
    const nuoviFiltri = { ...filtri };
    if (valore === '') {
      delete nuoviFiltri[campo];
    } else {
      nuoviFiltri[campo] = Number(valore);
    }
    onCambiaFiltri(nuoviFiltri);
  };

  return (
    <div className="filtri">
      <div className="campo">
        <label htmlFor="categoria">Categoria</label>
        <select
          id="categoria"
          value={filtri.categoriaId ?? ''}
          onChange={(e) => aggiornaCampo('categoriaId', e.target.value)}
        >
          <option value="">Tutte</option>
          {categorie.map((c) => (
            <option key={c.id} value={c.id}>{c.nome}</option>
          ))}
        </select>
      </div>

      <div className="campo">
        <label htmlFor="brand">Brand</label>
        <select
          id="brand"
          value={filtri.brandId ?? ''}
          onChange={(e) => aggiornaCampo('brandId', e.target.value)}
        >
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
          value={filtri.prezzoMin ?? ''}
          onChange={(e) => aggiornaCampo('prezzoMin', e.target.value)}
        />
      </div>

      <div className="campo">
        <label htmlFor="prezzoMax">Prezzo max (€)</label>
        <input
          id="prezzoMax"
          type="number"
          min="0"
          step="0.01"
          value={filtri.prezzoMax ?? ''}
          onChange={(e) => aggiornaCampo('prezzoMax', e.target.value)}
        />
      </div>

      <div className="azioni">
        <button onClick={onCerca}>Cerca</button>
        <button className="secondario" onClick={onReset}>Reset</button>
      </div>
    </div>
  );
}

export default FiltriProdotti;
