<template>
  <v-container
    id="dashboard-view"
    fluid
    tag="section"
  >
    <v-card>
      <v-row>
        <v-col>
          <v-card-title>
            Информация по счетам
          </v-card-title>
          <v-card-text>
            <Diagram
              v-if="loaded.summaryInformationOnInvoices"
              :chartdata="invoiceData.chartdata"
              :options="invoiceData.options"
            />
            <p
              v-else
            >
              Диаграмма недоступна
            </p>
          </v-card-text>
        </v-col>
        <v-col>
          <v-card-title>
            Информация по валютам
          </v-card-title>
          <v-card-text>
            <LineChart
              v-if="loaded.summaryInformationOnCurrencies"
              :chartdata="currencyData.chartdata"
              :options="currencyData.options"
            />
            <p
              v-else
            >
              Диаграмма недоступна
            </p>
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
  import Diagram from '../components/charts/Diagram'
  import LineChart from '../components/charts/LineChart'
  import AnalyticService from '@/services/analytic.service'

  export default {
    name: 'Profile',
    data () {
      return {
        Diagram: Diagram,
        LineChart: LineChart,
        invoiceData: {
          chartdata: {
            labels: [],
            datasets: [
              {
                backgroundColor: [],
                data: [],
              },
            ],
          },
          options: {
            responsive: true,
            maintainAspectRatio: false,
          },
        },
        currencyData: {
          chartdata: {
            labels: [],
            datasets: [
              {
                backgroundColor: [],
                data: [],
              },
            ],
          },
          options: {
            responsive: true,
            maintainAspectRatio: false,
          },
        },
        loaded: {
          summaryInformationOnInvoices: false,
          summaryInformationOnCurrencies: false,
        },
      }
    },
    computed: {
      currentUser () {
        return this.$store.state.auth.user
      },
    },
    mounted () {
      if (!this.currentUser) {
        this.$router.push('/signin')
      }

      this.getSummaryInformationOnInvoices()
      this.getSummaryInformationOnCurrencies()
    },
    methods: {
      getSummaryInformationOnInvoices () {
        AnalyticService.getSummaryInformationOnInvoices().then(r => {
          this.invoiceData.chartdata.labels = r.data.labels
          this.invoiceData.chartdata.datasets[0].data = r.data.datasets
          const backgroundColor = new Array(r.data.datasets.length)
          for (let i = 0; i < backgroundColor.length; i++) {
            backgroundColor[i] = this.getRandomColor()
          }
          this.invoiceData.chartdata.datasets[0].backgroundColor = backgroundColor
          if (r.data.datasets.length > 0) { this.loaded.summaryInformationOnInvoices = true }
        })
      },
      getSummaryInformationOnCurrencies () {
        AnalyticService.getSummaryInformationOnCurrencies().then(r => {
          this.currencyData.chartdata.labels = r.data.labels
          this.currencyData.chartdata.datasets[0].data = r.data.datasets
          const backgroundColor = new Array(r.data.datasets.length)
          for (let i = 0; i < backgroundColor.length; i++) {
            backgroundColor[i] = this.getRandomColor()
          }
          this.currencyData.chartdata.datasets[0].backgroundColor = backgroundColor
          if (r.data.datasets.length > 0) { this.loaded.summaryInformationOnCurrencies = true }
        })
      },
      getRandomColor () {
        var letters = '0123456789ABCDEF'
        var color = '#'
        for (var i = 0; i < 6; i++) {
          color += letters[Math.floor(Math.random() * 16)]
        }
        return color
      },
    },
  }
</script>
