import axios from 'axios'
import authHeader from './auth-header'
import GLOBAL from '../util/globals'

class InvoiceService {
  getInvoices () {
    const url = GLOBAL.API_SERVER + GLOBAL.INVOICE_PATH
    return axios.get(url, { headers: authHeader() })
  }

  getInvoicesByID (id) {
    const url = GLOBAL.API_SERVER + GLOBAL.INVOICE_PATH + '/' + id
    return axios.get(url, { headers: authHeader() })
  }

  updateInvoiceBalance (invoiceId, data) {
    const regex = /{[\w]*}/
    const UPDATE_INVOICE_BALANCE = GLOBAL.UPDATE_INVOICE_BALANCE.replace(regex, invoiceId)
    const url = GLOBAL.API_SERVER + GLOBAL.INVOICE_PATH + UPDATE_INVOICE_BALANCE

    return axios.patch(url, data, { headers: authHeader() })
  }

  closeInvoice (invoiceId) {
    const regex = /{[\w]*}/
    const url = GLOBAL.API_SERVER + GLOBAL.INVOICE_PATH + GLOBAL.INVOICE_ID.replace(regex, invoiceId)

    return axios.delete(url, { headers: authHeader() })
  }

  getCurrency () {
    const url = GLOBAL.API_SERVER + GLOBAL.CURRENCY_PATH
    return axios.get(url, { headers: authHeader() })
  }

  addInvoice (data) {
    const url = GLOBAL.API_SERVER + GLOBAL.INVOICE_PATH
    return axios.post(url, data, { headers: authHeader() })
  }
}

export default new InvoiceService()
