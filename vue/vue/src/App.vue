<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
  created () {
    var token = localStorage.getItem('token')
    if (token === undefined) {
      alert('请登录')
    }
  }

}
</script>

<style>
#app {
  margin-top: 2em;
}
</style>
