// Pathify
import { make } from 'vuex-pathify'
import GLOBALS from '../../util/globals'

// Data
const state = {
  drawer: null,
  drawerImage: true,
  mini: false,
  items: [
    {
      title: 'Главная',
      icon: 'mdi-view-dashboard',
      to: GLOBALS.DASHBOARD_PATH,
    },
    {
      title: 'Счета',
      icon: 'mdi-view-dashboard',
      to: GLOBALS.DASHBOARD_PATH + GLOBALS.INVOICE_PATH,
    },
    {
      title: 'Вклады',
      icon: 'mdi-account',
      to: GLOBALS.DASHBOARD_PATH,
    },
    {
      title: 'Кредиты',
      icon: 'mdi-clipboard-outline',
      to: GLOBALS.DASHBOARD_PATH,
    },
    {
      title: 'Переводы',
      icon: 'mdi-format-font',
      to: GLOBALS.DASHBOARD_PATH,
    },
    {
      title: 'Отчеты',
      icon: 'mdi-chart-bubble',
      to: GLOBALS.DASHBOARD_PATH,
    },
  ],
}

const mutations = make.mutations(state)

const actions = {
  ...make.actions(state),
  init: async ({ dispatch }) => {
    //
  },
}

const getters = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
