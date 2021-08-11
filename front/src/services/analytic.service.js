import axios from 'axios'
import authHeader from './auth-header'
import GLOBAL from '../util/globals'

class AnalyticService {
  getSummaryInformationOnInvoices () {
    const url = GLOBAL.API_SERVER + GLOBAL.ANALYTIC_PATH + GLOBAL.SUMMARY_INFORMATION_ON_INVOICES_PATH
    return axios.get(url, { headers: authHeader() })
  }

  getSummaryInformationOnCurrencies () {
    const url = GLOBAL.API_SERVER + GLOBAL.ANALYTIC_PATH + GLOBAL.SUMMARY_INFORMATION_ON_CURRENCIES_PATH
    return axios.get(url, { headers: authHeader() })
  }
}

export default new AnalyticService()
