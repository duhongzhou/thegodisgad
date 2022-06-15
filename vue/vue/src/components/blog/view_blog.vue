<template>
  <div id="view_blog" class="container">
    <div class="row">
      <div :key="index" v-for="(blog,index) in blogs" class="col-12 card">
        <div class="card-header" title="双击查看,么么哒" @dblclick="show(blog.blogId)">
          <h2>{{ blog.title }}</h2>
          <hr class="dropdown-divider">
          <div>
            <img :src="'http://10.90.246.218:8080/api/file/request/'+blog.headPortrait" height="45px" width="45px">
            <span>{{ blog.nickName }}</span>
            <small>发布于:{{ blog.gmtCreate }}</small>
          </div>
        </div>
        <div class="card-body">
          <div class="markdown-body hljs" v-html="blog.value"></div>

        </div>
        <div class="card-footer">
          <span class="badge rounded-pill text-dark bg-danger">好评数{{ blog.reviewNumber }}</span>
          <span class="badge rounded-pill text-dark bg-warning">差评数{{ blog.badReview }}</span>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import {marked} from 'marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/googlecode.css'

var rendererMD = new marked.Renderer()
marked.setOptions({
  highlight: function (code) {
    return hljs.highlightAuto(code).value
  },
  renderer: rendererMD,
  gfm: true,
  tables: true,
  breaks: false,
  pedantic: false,
  sanitize: false,
  smartLists: true,
  smartypants: false
})
export default {
  name: 'view_blog',
  created () {
    this.get_start_length(0, 10)
  },
  data: function () {
    return {
      blogs: []
    }
  },
  methods: {
    show (blogId) {
      window.location.href = 'http://localhost:8081/blog/show/?blogId=' + blogId
    },
    get_start_length (start, length) {
      this.$axios.get('/blog/serch/view', {
        params: {
          start: start,
          length: length
        }
      }).then(response => {
        if (response.data.code === '-1') {
          this.blogs = response.data.result
          this.blogs.forEach(value => {
            value.value = marked(value.value)
            // eslint-disable-next-line one-var
            const newDate = new Date(value.gmtCreate),
              oYear = newDate.getFullYear(),
              oMonth = newDate.getMonth() + 1,
              oDay = newDate.getDate(),
              oHour = newDate.getHours(),
              oMin = newDate.getMinutes(),
              oSen = newDate.getSeconds(),
              oTime = oYear + '-' + addZero(oMonth) + '-' + addZero(oDay) + ' ' + addZero(oHour) + ':' +
                addZero(oMin) + ':' + addZero(oSen)
            value.gmtCreate = oTime
          })
        }
      })
    }
  }
}

function addZero (num) {
  if (parseInt(num) < 10) {
    num = '0' + num
  }
  return num
}
</script>

<style lang="css">
#view_blog {
  margin-top: 10em;
}

p {
  color: #0b2e13;
}

p img {
  width: 300px;
  height: 300px;
}
</style>
