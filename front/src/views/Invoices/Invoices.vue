<template>
  <div>
    <component
      :is="Alert"
      v-if="records.length > 0"
      v-model="records"
      :records="records"
    />
    <v-row>
      <v-col>
        <v-card
          class="mx-auto"
        >
          <v-card-text>
            <h2 class="font-weight-bold">
              Выберите счет:
            </h2>
          </v-card-text>
          <v-container fluid>
            <v-row align="center">
              <v-col
                class="d-flex"
                cols="12"
                sm="6"
              >
                <v-select
                  v-model="selectedInvoiceId"
                  :items="invoices"
                  item-value="id"
                  item-text="customName"
                  label="Список счетов"
                  @change="changeInvoice"
                />
              </v-col>
            </v-row>
          </v-container>
          <v-card-text>
            <p>Информация по счету:</p>
            <p>
              Остаток на счете:
              <span
                v-if="isInvoiceSelected"
                class="font-weight-black"
              >{{ selectedInvoice.balance }} {{ selectedInvoice.currency }}
              </span>
            </p>
            <p>Валюта счета: <span v-if="isInvoiceSelected">{{ selectedInvoice.currency }}</span></p>
            <p>Дата открытия: <span v-if="isInvoiceSelected">{{ selectedInvoice.createdAt }}</span></p>
          </v-card-text>
          <v-card-actions>
            <v-row>
              <v-col>
                <v-btn-toggle>
                  <v-btn
                    depressed
                    color="primary"
                    @click="isActiveNew = true"
                  >
                    Открыть счет
                  </v-btn>
                  <v-btn
                    depressed
                    :disabled="!isInvoiceSelected"
                    @click="isActiveClose = true"
                  >
                    Закрыть счет
                  </v-btn>
                  <v-btn
                    depressed
                    :disabled="!isInvoiceSelected"
                    @click="isActiveUp = true"
                  >
                    Пополнить счет
                  </v-btn>
                </v-btn-toggle>
              </v-col>
            </v-row>
            <component
              :is="NewInvoice"
              :is-active="isActiveNew"
              @close-new="closeNew"
              @refresh-invoice="refreshInvoice"
            />
            <component
              :is="CloseInvoice"
              :is-active="isActiveClose"
              @close-close="closeClose"
              @refresh-invoice="refreshInvoice"
            />
            <component
              :is="InvoiceUp"
              :is-active="isActiveUp"
              @close-up="closeUp"
            />
          </v-card-actions>
        </v-card>
      </v-col>
      <v-col>
        <v-card
          class="mx-auto"
        >
          <v-card-text>
            <h2 class="font-weight-bold">
              История операций:
            </h2>
          </v-card-text>
          <v-card-text>
            <component
              :is="Alert"
              v-if="historyRecords.length > 0"
              v-model="historyRecords"
              :records="historyRecords"
            />
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>

  import InvoiceService from '../../services/invoice.service'
  import HistoryService from '../../services/histories.service'
  import InvoiceUp from '@/views/Invoices/InvoiceUp'
  import CloseInvoice from '@/views/Invoices/CloseInvoice'
  import NewInvoice from '@/views/Invoices/NewInvoice'
  import Alert from '@/views/Invoices/Alert'

  export default {
    name: 'Invoices',
    data () {
      return {
        invoices: [],
        selectedInvoiceId: null,
        isInvoiceSelected: false,
        InvoiceUp: InvoiceUp,
        CloseInvoice: CloseInvoice,
        NewInvoice: NewInvoice,
        Alert: Alert,
        isActiveUp: false,
        isActiveClose: false,
        isActiveNew: false,
        historyRecords: [],
      }
    },
    computed: {
      currentUser () {
        return this.$store.state.auth.user
      },
      selectedInvoice () {
        return this.$store.state.invoice.selectedInvoice
      },
      records () {
        return this.$store.state.invoice.records
      },
    },
    mounted () {
      if (!this.currentUser) {
        this.$router.push('/signin')
      }
      this.getInvoices()
      this.getHistories()
    },
    methods: {
      changeInvoice () {
        this.isInvoiceSelected = true
        this.$store.dispatch('invoice/setSelectedInvoice', this.getSelectedInvoice())
        this.$store.dispatch('invoice/setSelectedInvoiceId', this.selectedInvoiceId)
      },
      getSelectedInvoice () {
        for (const key of Object.keys(this.invoices)) {
          if (this.invoices[key].id === this.selectedInvoiceId) {
            return this.invoices[key]
          }
        }
      },
      closeUp (number) {
        this.isActiveUp = number
      },
      closeClose (number) {
        this.isActiveClose = number
      },
      closeNew (number) {
        this.isActiveNew = number
      },
      getInvoices () {
        InvoiceService.getInvoices().then(r => {
          this.invoices = r.data.map(function (item) {
            const customFields = {
              customName: item.name + ' [ id: ' + item.id + ' ]',
            }
            return Object.assign(customFields, item)
          })
        })
      },
      getHistories () {
        HistoryService.getHistories().then(r => {
          this.historyRecords = r.data.map(function (item) {
            return item.record
          })
        })
      },
      refreshInvoice () {
        this.getInvoices()
      },
    },
  }
</script>
