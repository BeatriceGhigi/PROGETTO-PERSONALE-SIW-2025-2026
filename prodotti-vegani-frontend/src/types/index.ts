export interface Prodotto {
  id: number;
  nome: string;
  descrizione: string;
  prezzo: number;
  formato: string;
  vegan: boolean;
  crueltyFree: boolean;
  skinType: string;
  brandNome: string | null;
  categoriaNome: string | null;
}

export interface Categoria {
  id: number;
  nome: string;
}

export interface Brand {
  id: number;
  nome: string;
}

export interface FiltriProdotto {
  categoriaId?: number;
  brandId?: number;
  prezzoMin?: number;
  prezzoMax?: number;
}
