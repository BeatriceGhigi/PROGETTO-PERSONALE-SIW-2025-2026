import api from './api';
import type { Prodotto, Categoria, Brand, FiltriProdotto } from '../types';

export async function getCategorie(): Promise<Categoria[]> {
  try {
    const { data } = await api.get<Categoria[]>('/categorie');
    return data;
  } catch {
    throw new Error('Impossibile caricare le categorie.');
  }
}

export async function getBrand(): Promise<Brand[]> {
  try {
    const { data } = await api.get<Brand[]>('/brand');
    return data;
  } catch {
    throw new Error('Impossibile caricare i brand.');
  }
}

export async function cercaProdotti(filtri: FiltriProdotto): Promise<Prodotto[]> {
  const params: Record<string, string> = {};

  if (filtri.categoriaId !== undefined) params.categoriaId = String(filtri.categoriaId);
  if (filtri.brandId !== undefined) params.brandId = String(filtri.brandId);
  if (filtri.prezzoMin !== undefined) params.prezzoMin = String(filtri.prezzoMin);
  if (filtri.prezzoMax !== undefined) params.prezzoMax = String(filtri.prezzoMax);

  try {
    const { data } = await api.get<Prodotto[]>('/prodotti', { params });
    return data;
  } catch {
    throw new Error('Impossibile caricare i prodotti.');
  }
}
