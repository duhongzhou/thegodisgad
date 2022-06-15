<template>
  <div class="container" style="width: 60%;margin-bottom: 3em;">
    <!--    v-highlight-->
    <div>
      <div class="row">
        <h2 class="col-12">{{ blog.title }}</h2>
      </div>
      <hr class="dropdown-divider">
      <div class="border border-secondary  row">
        <div class="col-1">
          <img :src="'http://10.90.246.218:8080/api/file/request/'+blog.headPortrait"
               class="rounded .img-thumbnail d-block " height="45px"
               width="45px"
          >
        </div>
        <div class="col" style="padding-left: 0">
          <div>
            <span class="text-info" href="#">{{ blog.nickName }}</span>
            <small>发布于:{{ blog.gmtCreate }}</small>
          </div>
          <div>

            <button class="badge rounded-pill text-dark btn btn-outline-danger" title="还不错哎" @click="good(blog)">好评数{{
                blog.goodReview
              }}
            </button>
            <button class="badge rounded-pill text-dark btn btn-outline-warning" title="报好" @click="no_good(blog)">差评数{{
                blog.badReview
              }}
            </button>
          </div>
        </div>
      </div>
      <div class="border border-secondary border-top-0 row" style="margin-bottom: 5em">
        <div id="content" v-highlight class="markdown-body hljs col" v-html="blog.value "></div>
      </div>
    </div>
    <div>
      <input v-model="review" class="form-control border-success" href="re" placeholder="评论" type="text">
      <input class="form-control" type="button" value="评论" @click="add_re">
    </div>
    <div>
      <div v-for="(value,index) in blogs" :key="index">
        <div>
          <div class="row">
            <h2 class="col-12">{{ value.title }}</h2>
          </div>
          <hr class="dropdown-divider">
          <div class="border border-secondary  row">
            <div class="col-1">
              <img :src="'http://10.90.246.218:8080/api/file/request/'+blog.headPortrait"
                   class="rounded .img-thumbnail d-block " height="45px"
                   width="45px"
              >
            </div>
            <div class="col" style="padding-left: 0">
              <div>
                <span class="text-info" href="#">{{ value.nickName }}</span>
                <small>发布于:{{ value.gmtCreate }}</small>
              </div>
              <div>
                <button class="badge rounded-pill text-dark btn btn-outline-danger" title="还不错哎" @click="good(value)">
                  好评数{{
                    value.goodReview
                  }}
                </button>
                <button class="badge rounded-pill text-dark btn btn-outline-warning" title="报好"
                        @click="no_good(value)">
                  差评数{{
                    value.badReview
                  }}
                </button>
                <button class="badge rounded-pill text-dark btn btn-outline-warning" title="评论"
                        @click="re(value,index)">评论
                </button>
              </div>
            </div>
          </div>
          <div class="border border-secondary border-top-0 row">
            <div id="content" v-highlight class="markdown-body hljs col" v-html="value.value "></div>
          </div>
        </div>
        <div v-if="ins==index">
          <input v-model="review" class="form-control border-success" href="re" placeholder="评论" type="text">
          <input class="form-control" type="button" value="发表" @click="add_re">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {marked} from 'marked'
// import Vue from 'vue'
import hljs from 'highlight.js'
import 'highlight.js/styles/googlecode.css'

import Vue from 'vue'

export default {
  name: 'show_blog',
  created () {
    this.get_blog_id()
  },
  data () {
    return {
      blogs: [],
      blog: {},
      blogId: '',
      value: '',
      reblogId: '',
      review: null,
      reblog: {},
      ins: -1
    }
  },
  methods: {
    add_re: function () {
      if (this.review !== null) {
        var s
        if (this.reblog.rootBlogId !== 0) {
          s = this.reblog.rootBlogId
        } else {
          s = this.reblog.blogId
        }
        var blog = {
          parentalBlogId: this.reblog.blogId,
          rootBlogId: s,
          title: '回复:' + this.reblog.nickName,
          value: this.review
        }
        this.$axios.post('/blog/add', blog, {
          params: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
          if (response.data.code === '-1') {
            alert('评论成功,刷新可以查看!')
          } else {
            alert(response.data.message)
          }
        })
      } else {
        alert('请输入内容')
      }
    },
    re: function (blog, index) {
      this.ins = index
      this.reblogId = blog.blogId
      this.reblog = blog
      this.review = null
    },
    good: function (blog) {
      this.$axios.get('/blog/operation/2/1', {
        params: {
          blogId: blog.blogId,
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.code === '-1') {
          blog.goodReview++
        }
      })
    },
    no_good: function (blog) {
      this.$axios.get('/blog/operation/3/1', {
        params: {
          blogId: blog.blogId,
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.code === '-1') {
          blog.badReview++
        }
      })
    },
    get_blog_id: function () {
      var blogId = this.$route.query.blogId

      if (blogId !== undefined) {
        this.reblogId = blogId
        this.$axios.get('/blog/view/' + blogId, {
          params: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
          var data = response.data
          if (response.data.code === '-1') {
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
            this.blogs = data.result
            console.log(this.blogs)
            this.blogs.forEach((value, index) => {
              if (index === 0) {
                value.value = marked(value.value)
                this.reblog = value
                this.blog = value
              }
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
            this.blogs.splice(0, 1)
          } else {
            alert(data.message)
            window.location.href = '/'
          }
        })
      }
    }
  }
}

function addZero (num) {
  if (parseInt(num) < 10) {
    num = '0' + num
  }
  return num
}

Vue.directive('highlight', function (el) {
  let blocks = el.querySelectorAll('pre code')
  blocks.forEach((block) => {
    hljs.highlightBlock(block)
  })
})

</script>

<style>

p {
  text-align: left;
}

body {
  text-align: left;
}

/*pre {*/
/*  background-color: black;*/
/*}*/
</style>
