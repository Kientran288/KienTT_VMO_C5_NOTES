import http from "@/services/http-common";
import axios from "axios";

class ProductDataService {
  getAll() {
    return http.get("/products");
  }

  get(id: string) {
    return http.get(`/products/${id}`);
  }

  create(data: any) {
    return http.post("/products", data);
  }

  update(id: any, data: any) {
    return http.put(`/products/${id}`, data);
  }

  delete(id: any) {
    return http.delete(`/products/${id}`);
  }

  deleteAll() {
    return http.delete(`/products`);
  }

  findByTitle(name: string) {
    return http.get(`/products?name=${name}`);
  }
  export() {
    return axios({
      url: 'http://localhost:8080/api-kientt/products/export/pdf',
      method: 'GET',
      responseType: 'blob',
    });
  }
}

export default new ProductDataService();