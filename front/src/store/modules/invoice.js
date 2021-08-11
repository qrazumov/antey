const state = {
  selectedInvoiceId: null,
  selectedInvoice: null,
  records: [],
}

const mutations = {
  setSelectedInvoiceId (state, invoiceId) {
    state.selectedInvoiceId = invoiceId
  },
  setSelectedInvoice (state, invoiceId) {
    state.selectedInvoice = invoiceId
  },
  setRecords (state, records) {
    state.records.push(records)
  },
}

const actions = {
  setSelectedInvoiceId ({ commit }, invoiceId) {
    commit('setSelectedInvoiceId', invoiceId)
  },
  setSelectedInvoice ({ commit }, invoiceId) {
    commit('setSelectedInvoice', invoiceId)
  },
  setRecords ({ commit }, records) {
    commit('setRecords', records)
  },
}

const getters = {
  selectedInvoiceId: state => {
    return state.selectedInvoiceId
  },
  selectedInvoice: state => {
    return state.selectedInvoice
  },
  records: state => {
    return state.records
  },
}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
