<template>
  <div>
    <h5 class="text-left">个人标签</h5>
    <div id="show" class="row-cols-4">
      <span v-for="(tag,index) in tags"
            :class="tag_class[index]"
            class="badge rounded-pill " :key="index">{{
          tag.tagName
        }}:{{ tag.tagProperty }}</span>
    </div>
    <div id="add " class="input-group">
      <input v-model="tagName" class="form-control" placeholder="标签名" type="text">
      <button class="btn btn-outline-secondary" type="button" @click="add_tag()">添加</button>
      <input v-model="tagProperty" class="form-control" placeholder="标签内容" type="text">
    </div>
  </div>
</template>

<script>
export default {
  name: 'user_tag',
  created () {
    this.have()
  },
  data: function () {
    return {
      tag_class: [],
      tags: [],
      tagName: '',
      tagProperty: ''
    }
  },
  watch: {
    tags (newval, oldval) {
      if(newval===oldval)
      {
        console.log('12')
      }
      this.radom()
    }
  },
  methods: {
    radom: function () {
      this.tag_class = []
      if (this.tags != null) {
        for (var i = 0; i < this.tags.length; i++) {
          var num = Math.floor(Math.random() * 100) % 6
          switch (num) {
            case 0:
              this.tag_class.push('bg-info  text-dark')
              break
            case 1:
              this.tag_class.push('bg-primary  text-dark')
              break
            case 2:
              this.tag_class.push('bg-secondary  text-dark')
              break
            case 3:
              this.tag_class.push('bg-success  text-dark')
              break
            case 4:
              this.tag_class.push('bg-warning  text-dark')
              break
            case 5:
              this.tag_class.push('bg-danger  text-dark')
              break
          }
        }
      }
    },
    get_all_tag: function () {
      this.$axios.get('/tag/user/have', {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        this.tags = response.data.result
      })
    },
    add_tag: function () {
      this.$axios.post('/tag/user/add', {
        tagName: this.tagName,
        tagProperty: this.tagProperty
      }, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        var data = response.data
        console.log(data)
        if (data.code == '-1') {
          this.tags.push(data.result)
        } else {
          alert(data.message)
        }
      })
    },
    have: function () {
      this.$axios.get('/tag/user/have', {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.result !== null) {
          this.tags = response.data.result
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
