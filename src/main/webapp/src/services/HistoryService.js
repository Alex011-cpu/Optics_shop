import AuthService from "./AuthService";
import axios from "axios";

const API_URL = "http://localhost:8080/history/";
const TOKEN = AuthService.authHeader()

class HistoryService {


    addToHistory(user) {

        return axios.post(API_URL + "addInHistory", {
                email: user.email,
                username: user.username,
                password: user.password
            },
            { headers: TOKEN }).then(r => {
            return r.data})
    }

    getHistory() {
        return axios.get(API_URL + "orders",{ headers: TOKEN }).then(r => {
            return r.data})
    }
}

export default new HistoryService()