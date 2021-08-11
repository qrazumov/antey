<template>
  <v-row
    justify="center"
  >
    <v-col
      sm="3"
    >
      <v-card>
        <v-card-text>
          <p class="text-h4 text--primary">
            Форма авторизации
          </p>
          <v-form
            ref="form"
            lazy-validation
          >
            <v-text-field
              v-model="user.username"
              label="Логин"
              required
            />

            <v-text-field
              v-model="user.password"
              label="Пароль"
              required
              type="Password"
            />

            <v-btn
              color="success"
              class="mr-4"
              @click="handleLogin"
            >
              Вход
            </v-btn>
          </v-form>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>

  import User from '../models/user'
  import GLOBALS from '../util/globals'

  export default {
    name: 'Login',
    data () {
      return {
        user: new User('qrazumov', '', '12345678'),
        loading: false,
        message: '',
      }
    },
    computed: {
      loggedIn () {
        return this.$store.state.auth.status.loggedIn
      },
    },
    created () {
      if (this.loggedIn) {
        this.$router.push(GLOBALS.DASHBOARD_PATH)
      }
    },
    methods: {
      handleLogin () {
        this.loading = true

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/login', this.user).then(
            () => {
              this.$router.push(GLOBALS.DASHBOARD_PATH)
            },
            error => {
              this.loading = false
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString()
            },
          )
        }
        // })
      },
    },
  }

</script>
