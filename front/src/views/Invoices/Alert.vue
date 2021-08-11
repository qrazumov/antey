<template>
  <div>
    <v-alert
      v-for="recordsi in records"
      :key="recordsi"
      dense
      text
      type="success"
      dismissible
    >
      {{ recordsi }}
    </v-alert>
  </div>
</template>

<script>

  export default {
    name: 'Alert',
    props: {
      records: {
        type: Array,
      },
    },
    data () {
      return {
      }
    },
    computed: {
    },
    mounted () {
    },
    methods: {
    },
  }
</script>
