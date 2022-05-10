import axios from "axios";



const API_URL = "/product/";


class ProductService {


   getProductsList(category) {
        return axios.get(API_URL + category).then(r => {
            return r.data
        })
    }

    getProductListWithSort(category, sortMethod) {
        return axios.get(API_URL + category + "/" + sortMethod).then(r => {
            return r.data
        })
    }

    getProductsCount(category) {
        return axios.get(API_URL + "count/" + category).then(r => {
            const res = r;
            return r.data
        })
    }
}

export default new ProductService()