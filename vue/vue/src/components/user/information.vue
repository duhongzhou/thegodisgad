<template>
  <div class="container">
    <Show_base_info></Show_base_info>
    <Show_user_school style="margin-top: 10px"></Show_user_school>
    <User_tag></User_tag>
    <show_all_address style="margin-top: 10px"></show_all_address>
  </div>
</template>

<script>
// eslint-disable-next-line camelcase
import Show_base_info from './show_base_info.vue'
// eslint-disable-next-line camelcase
import show_all_address from '../address/show_all_address.vue'
import User_tag from './user_tag.vue'
// eslint-disable-next-line camelcase
import Show_user_school from '../school/show_user_school.vue'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'information',
  components: {
    Show_base_info,
    show_all_address,
    User_tag,
    Show_user_school
  }
}
</script>

<style scoped>

</style>
