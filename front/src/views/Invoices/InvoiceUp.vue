<template>
  <v-dialog
    v-model="isActive"
    width="500"
    @click:outside="clickOutside"
  >
    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        Пополнить счет
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
                v-model="balance"
                counter="25"
                hint="Введите сумму"
                label="0"
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
          @click="upInvoice"
        >
          Пополнить
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>

  import InvoiceService from '@/services/invoice.service'

  export default {
    name: 'InvoiceUp',
    props: {
      isActive: {
        type: Boolean,
        default: false,
      },
    },
    data () {
      return {
        dialog: false,
        balance: null,
      }
    },
    computed: {
      currentUser () {
        return this.$store.state.auth.user
      },
    },
    mounted () {
    },
    methods: {
      clickOutside () {
        this.$emit('close-up', false)
      },
      upInvoice () {
        this.clickOutside()
        const invoiceId = this.$store.getters['invoice/selectedInvoiceId']
        const data = {
          balance: this.balance,
        }
        InvoiceService.updateInvoiceBalance(invoiceId, data).then(r => {
          this.$store.dispatch('invoice/setSelectedInvoice', r.data.invoice)
          this.$store.dispatch('invoice/setRecords', r.data.history.record)
        })
      },

    },
  }
</script>
