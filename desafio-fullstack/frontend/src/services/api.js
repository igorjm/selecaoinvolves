import axios from 'axios'

//Define conexao com backend
const api = axios.create({
    baseURL: 'http://localhost:8080/alertas'
})

export default api