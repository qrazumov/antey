<script>

  import { PolarArea } from 'vue-chartjs'

  export default {
    name: 'Diagram',
    extends: PolarArea,
    props: {
      chartdata: {
        type: Object,
        default: null,
      },
      options: {
        type: Object,
        default: null,
      },
    },
    data: () => ({
    }),
    mounted () {
      this.renderChart(this.chartdata, this.options)
    },
  }
</script>
