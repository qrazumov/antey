const EN_LOCALE_ONLY = process.env.EN_LOCALE_ONLY === 'true'
const IN_BROWSER = typeof window !== 'undefined'
const IS_DEBUG = process.env.DEBUG === 'true'
const IS_PROD = process.env.NODE_ENV === 'production'
let API_SERVER

if (IS_PROD) {
  API_SERVER = '/api'
} else {
  API_SERVER = 'http://localhost:8080/api'
}

const API_SERVER_SIGNIN = API_SERVER + '/auth/signin'
const API_SERVER_SIGNUP = API_SERVER + '/auth/signup'

const DASHBOARD_PATH = '/my'

const INVOICE_PATH = '/invoices'
const INVOICE_ID = '/{invoice_id}'
const UPDATE_INVOICE_BALANCE = INVOICE_ID + '/balance'

const CURRENCY_PATH = '/currency'

const HISTORY_PATH = '/histories'

const ANALYTIC_PATH = '/analytics'
const SUMMARY_INFORMATION_ON_INVOICES_PATH = '/summary-information-on-invoices'
const SUMMARY_INFORMATION_ON_CURRENCIES_PATH = '/summary-information-on-currencies'

module.exports = {
  EN_LOCALE_ONLY,
  IN_BROWSER,
  IS_DEBUG,
  IS_PROD,
  API_SERVER,
  API_SERVER_SIGNIN,
  API_SERVER_SIGNUP,
  DASHBOARD_PATH,
  UPDATE_INVOICE_BALANCE,
  INVOICE_PATH,
  INVOICE_ID,
  CURRENCY_PATH,
  HISTORY_PATH,
  ANALYTIC_PATH,
  SUMMARY_INFORMATION_ON_INVOICES_PATH,
  SUMMARY_INFORMATION_ON_CURRENCIES_PATH,
}
