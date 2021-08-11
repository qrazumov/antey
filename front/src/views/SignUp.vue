<template>
  <v-row justify="center">
    <v-col cols="6">
      <v-card>
        <v-card-text>
          <p class="text-h4 text--primary">
            Форма регистрации
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
              v-model="user.email"
              label="Почта"
              required
              type="email"
            />

            <v-text-field
              v-model="user.password"
              label="Пароль"
              required
              type="Password"
            />
            <v-btn-toggle>
              <v-btn
                color="warning"
                @click="handleRegister"
              >
                Регистрация
              </v-btn>

              <v-btn
                color="primary"
                @click="$router.push('/signin')"
              >
                Авторизация
              </v-btn>
            </v-btn-toggle>
          </v-form>
        </v-card-text>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>

  import GLOBALS from '@/util/globals'
  import User from '@/models/user'

  export default {
    name: 'SignUpView',

    data () {
      return {
        user: new User('qrazumov', 'qrazumov@gmail.com', '12345678'),
        loading: false,
        message: '',
      }
    },

    computed: {
    },
    metaInfo: {
      titleTemplate: 'Регистрация',
    },
    methods: {
      handleRegister () {
        this.loading = true

        if (this.user.username && this.user.password) {
          this.$store.dispatch('auth/register', this.user).then(
            (r) => {
              // this.$router.push(GLOBALS.DASHBOARD_PATH)
              alert(r.message)
            },
            error => {
              this.loading = false
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString()
              alert(error.response.data.message)
            },
          )
        }
        // })
      },
    },
  }
</script>
