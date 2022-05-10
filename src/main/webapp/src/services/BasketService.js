import axios from "axios";
import AuthService from "./AuthService";


const API_URL = "/basket/";
const TOKEN = AuthService.authHeader()

class BasketService {


    getCountProducts() {
        return axios.get(API_URL + "countProducts",
            { headers: TOKEN }).then(r => {
            return r.data
        })
    }

    addProductInBasket(product) {
        return axios.post(API_URL + "addInBasket", {
                id:product.id,
                category: product.category,
                brandName: product.brandName,
                price: product.price,
                path: product.path
            },
            { headers: TOKEN }).then(r => {
            return r.data})
    }

    delProductInBasket(productId) {
        return axios.delete(API_URL + "delete/" + productId, { headers: TOKEN }).then(r => {
            return r.data})
    }

    deleteAll() {
        return axios.delete(API_URL + "deleteAll",{ headers: TOKEN }).then(r => {
            return r.data
        })
    }

    getBasketList() {
        return axios.get(API_URL + "cartProducts",
            { headers: TOKEN }).then(r => {
                return r.data
        })
    }

    getSumOfOrder() {
        return axios.get(API_URL + "sumOfOrder",
            { headers: TOKEN }).then(r => {
            return r.data
        })
    }

    getBasketByProductId(id) {
        return axios.get(API_URL + "product/" + id,
            { headers: TOKEN }).then(r => {
            return r.data
        })
    }
}

export default new BasketService();