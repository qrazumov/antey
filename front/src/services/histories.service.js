import axios from 'axios'
import authHeader from './auth-header'
import GLOBAL from '../util/globals'

class HistoriesService {
  getHistories () {
    const url = GLOBAL.API_SERVER + GLOBAL.HISTORY_PATH
    return axios.get(url, { headers: authHeader() })
  }
}

export default new HistoriesService()
