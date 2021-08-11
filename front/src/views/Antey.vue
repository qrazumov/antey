<template>
  <v-container
    id="dashboard-view"
    fluid
    tag="section"
  >
    <v-card
      class="mx-auto"
      max-width="500"
    >
      <v-img
        src="../assets/antey.jpg"
        height="200px"
      />

      <v-card-title>
        antey bank
      </v-card-title>

      <v-card-subtitle>
        <p>личный кабинет</p>

        <p class="text-h1 font-weight-black red--text">
          * java 16
        </p>
        <div class="red grey darken-1">
          <p class="text-h2 font-weight-black orange--text">
            * spring boot
          </p>
        </div>
        <p class="text-h3 font-weight-black yellow--text">
          * spring security
        </p>
        <p class="text-h4 font-weight-black green--text">
          * maven
        </p>
        <p class="text-h5 font-weight-black blue--text">
          * hibernate orm
        </p>
        <p class="text-h6 font-weight-black purple--text">
          * postgresql
        </p>
        <p class="text-h7 font-weight-black red--text">
          * vuejs + vuetify + vuex
        </p>
        <div class="red grey darken-1">
          <p class="text-h8 font-weight-black orange--text">
            * spa app
          </p>
        </div>
        <div class="red grey darken-1">
          <p class="text-h9 font-weight-black yellow--text">
            * restful api
          </p>
        </div>
      </v-card-subtitle>

      <v-card-actions>
        <v-btn-toggle>
          <v-btn
            color="primary"
            @click="$router.push('signin')"
          >
            <v-icon
              left
            >
              mdi-login-variant
            </v-icon>
            Авторизация
          </v-btn>
          <v-btn
            @click="$router.push('signup')"
          >
            <v-icon
              left
            >
              mdi-account-plus
            </v-icon>

            Регистрация
          </v-btn>
        </v-btn-toggle>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
  import GLOBALS from '../util/globals'
  export default {
    name: 'AnteyView',
    data: () => ({
      DASHBOARD_PATH: GLOBALS.DASHBOARD_PATH,
    }),
    computed: {},
  }
</script>
