<template>
  <v-menu
    bottom
    left
    min-width="200"
    offset-y
    origin="top right"
    transition="scale-transition"
  >
    <template v-slot:activator="{ attrs, on }">
      <v-btn
        class="ml-2"
        min-width="0"
        text
        v-bind="attrs"
        v-on="on"
      >
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </template>

    <v-list
      :tile="false"
      flat
      nav
    >
      <template v-for="(p, i) in profile">
        <v-divider
          v-if="p.divider"
          :key="`divider-${i}`"
          class="mb-2 mt-2"
        />

        <app-bar-item
          v-else
          :key="`item-${i}`"
          :to="p.to"
        >
          <v-list-item-title v-text="p.title" />
        </app-bar-item>
      </template>
    </v-list>
  </v-menu>
</template>

<script>
  import GLOBALS from '../../../util/globals'
  const username = JSON.parse(localStorage.getItem('user')).username

  export default {
    name: 'DefaultAccount',

    data: () => ({
      profile: [
        {
          title: username,
          to: GLOBALS.DASHBOARD_PATH,
        },
        {
          title: 'Settings',
          to: GLOBALS.DASHBOARD_PATH,
        },
        {
          divider: true,
          to: GLOBALS.DASHBOARD_PATH,
        },
        {
          title: 'Выход',
          to: '/logout',
        },
      ],
    }),
  }
</script>
