<template>
  <v-app>
    <default-view />

    <default-footer />
  </v-app>
</template>

<script>
  export default {
    name: 'EmptyLayout',

    components: {
      DefaultBar: () => import(
        /* webpackChunkName: "default-app-bar" */
        '../default/AppBar'
      ),

      DefaultFooter: () => import(
        /* webpackChunkName: "default-footer" */
        '../default/Footer'
      ),
      DefaultSettings: () => import(
        /* webpackChunkName: "default-settings" */
        '../default/Settings'
      ),
      DefaultView: () => import(
        /* webpackChunkName: "default-view" */
        '../default/View'
      ),
    },
  }
</script>
