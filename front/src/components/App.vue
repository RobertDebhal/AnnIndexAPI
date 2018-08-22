<template>
  <div id="app">
  <SideNav />
  <TopBar />
  <router-view></router-view>
  </div>
</template>

<script>
import SideNav from './SideNav.vue'
import TopBar from './TopBar.vue'
import ProductLook from './ProductLook.vue'

export default {
  name: 'App',
  components: {
    SideNav,
    TopBar,
    ProductLook
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

body {
  margin: 0;
}

</style>