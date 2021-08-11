<template>
  <v-dialog
    v-model="isActive"
    width="500"
    @click:outside="clickOutside"
  >
    <v-card>
      <v-card-title class="text-h5 grey lighten-2">
        Закрыть счет
      </v-card-title>
      <v-card-text>
        <v-container fluid>
          <v-row align="center">
            <v-col
              class="d-flex"
              cols="12"
              sm="6"
            >
              <p>Вы уверены, что хотите закрыть счет?</p>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn
          color="primary"
          text
          @click="closeInvoice"
        >
          Закрыть
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
        dialog: false,
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
      closeInvoice () {
        this.clickOutside()
        const invoiceId = this.$store.getters['invoice/selectedInvoiceId']
        InvoiceService.closeInvoice(invoiceId).then(r => {
          this.$store.dispatch('invoice/setRecords', r.data.record)
          this.$emit('refresh-invoice')
        })
      },
      clickOutside () {
        this.$emit('close-close', false)
      },
    },
  }
</script>
