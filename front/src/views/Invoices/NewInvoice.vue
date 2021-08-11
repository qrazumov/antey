<template>
  <v-dialog
    v-model="isActive"
    width="500"
    @click:outside="clickOutside"
  >
    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        Открыть счет
      </v-card-title>
      <v-card-text>
        <v-container fluid>
          <v-row align="center">
            <v-col
              class="d-flex"
              cols="12"
              sm="6"
            >
              <v-text-field
                v-model="name"
                counter="25"
                hint="Введите название"
                label="Название счета"
              />
            </v-col>
            <v-col
              class="d-flex"
              cols="12"
              sm="6"
            >
              <v-select
                v-model="selectedCurrency"
                :items="currency"
                label="Валюта счета"
              />
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn
          color="primary"
          text
          @click="newInvoice"
        >
          Открыть счет
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>

  import InvoiceService from '@/services/invoice.service'

  export default {
    name: 'CloseInvoices',
    props: {
      isActive: {
        type: Boolean,
        default: false,
      },
    },
    data () {
      return {
        currency: null,
        selectedCurrency: null,
        name: null,
      }
    },
    computed: {
      currentUser () {
        return this.$store.state.auth.user
      },
    },
    mounted () {
      this.getCurrency()
    },
    methods: {
      newInvoice () {
        this.clickOutside()
        const data = {
          name: this.name,
          currency: this.selectedCurrency,
          balance: 0,
        }
        // TODO
        InvoiceService.addInvoice(data).then(r => {
          this.$store.dispatch('invoice/setRecords', r.data.history.record)
          this.$emit('refresh-invoice')
        })
      },
      clickOutside () {
        this.$emit('close-new', false)
      },
      getCurrency () {
        InvoiceService.getCurrency().then(r => {
          this.currency = r.data
        })
      },
    },
  }
</script>
