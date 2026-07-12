import type { Prodotto } from '../types';

interface ListaProdottiProps {
  prodotti: Prodotto[];
}

function ListaProdotti({ prodotti }: ListaProdottiProps) {
  if (prodotti.length === 0) {
    return <p className="risultati-count">Nessun prodotto trovato con questi filtri.</p>;
  }

  return (
    <>
      <p className="risultati-count">{prodotti.length} prodotti trovati</p>
      <div className="griglia-prodotti">
        {prodotti.map((p) => (
          <div className="card-prodotto" key={p.id}>
            <h3>{p.nome}</h3>
            <p className="brand-nome">{p.brandNome} · {p.categoriaNome}</p>
            <p className="prezzo">{p.prezzo.toFixed(2)} €</p>
            <p className="formato">{p.formato}</p>
            <div className="badge-container">
              {p.vegan && <span className="badge">Vegan</span>}
              {p.crueltyFree && <span className="badge">Cruelty Free</span>}
            </div>
          </div>
        ))}
      </div>
    </>
  );
}

export default ListaProdotti;
