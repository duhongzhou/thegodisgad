<template>
  <div>

    <div id="editor">

      <mavon-editor ref="md" v-model="push.articleBody" class="mavonEditor" style="height: 100%" @imgAdd="imgAdd"
                    @imgDel="imgDel"
                    @save="updateArticle"/>
      <div class="container-fluid " style="margin-top: 2em">
        <input v-model="push.articleName" class="form-control" type="text">
        <input class="form-control" type="button" value="发表" @click="updateArticle">
        <a v-if="url!==''" :href="url">查看地址</a>
      </div>
    </div>

  </div>

</template>

<script>
export default {
  name: 'add_blog',
  data () {
    return {
      push: {
        articleBody: '',
        articleDescription: '',
        articleFlag: 0,
        articleId: 0,
        articleImgUrl: '',
        articleName: '',
        classifyIdList: [
          1
        ],
        collectStatus: true,
        commentStatus: true,
        starStatus: true

      },
      url: ''
    }
  },
  components: {
    // mavonEditor
    // or 'mavon-editor': mavonEditor
  },
  methods: {
    imgAdd: async function (pos, $file) {
      let formdata = new FormData()

      formdata.append('file', $file)
      await this.$axios.post('/file/upload', formdata, {
        'Content-Type': 'multipart/form-data;charset=UTF-8',
        'aaa': 'aaa',
        params: {
          token: localStorage.getItem('token')
        }
      }
      ).then((response) => {
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        //    this.$vm.$img2Url(pos, url.data);
        // alert(response.data.code)
        if (response.data.code === '-1') {
          this.$refs.md.$img2Url(pos, response.data.result)
          alert('添加图成功')
        }
      })
    },
    imgDel (pos) {
      delete this.imgFile[pos]
    },
    updateArticle () {
      var add = {
        value: '',
        articleDescription: '',
        articleFlag: 0,
        articleImgUrl: '',
        title: '',
        classifyIdList: [
          1
        ],
        collectStatus: true,
        commentStatus: true,
        starStatus: true
      }
      add.value = this.push.articleBody
      add.articleDescription = this.push.articleDescription
      add.articleFlag = this.push.articleFlag
      add.articleImgUrl = this.push.articleImgUrl
      add.title = this.push.articleName
      add.classifyIdList = this.push.classifyIdList
      add.collectStatus = this.push.collectStatus
      add.commentStatus = this.push.commentStatus
      add.starStatus = this.push.starStatus
      console.log(add)
      this.$axios.post(
        '/blog/add',
        add, {
          params: {
            token: localStorage.getItem('token')
          }
        }).then((response) => {
        if (response.data.code === '-1') {
          alert(response.data.message)
          this.url = response.data.result
        } else {
          alert(response.data.message)
        }
      })
    }

  }

}
</script>

<style lang="css" scoped>
.mavonEditor {
  width: 100%;
  height: 100%;
}
</style>
