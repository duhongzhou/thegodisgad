<template>

  <div class="container">
    <Show_all_address :key="componentKey"></Show_all_address>
    <!--    <form>-->
    <!--      <div class="row g-3">-->
    <!--        <div class="control col-12">-->
    <!--          <label class="form_lable" for="province"> 省&ensp;&ensp; </label>-->
    <!--          <select id="province" v-model="province" class="form-select form-control" name="province"-->
    <!--                  @change="selectProvince">-->
    <!--            <option selected>请选择</option>-->
    <!--            <option value="北京市">北京市</option>-->
    <!--            <option value="天津市">天津市</option>-->
    <!--            <option value="河北省">河北省</option>-->
    <!--            <option value="山西省">山西省</option>-->
    <!--            <option value="内蒙古自治区">内蒙古自治区</option>-->
    <!--            <option value="辽宁省">辽宁省</option>-->
    <!--            <option value="吉林省">吉林省</option>-->
    <!--            <option value="黑龙江省">黑龙江省</option>-->
    <!--            <option value="上海市">上海市</option>-->
    <!--            <option value="江苏省">江苏省</option>-->
    <!--            <option value="浙江省">浙江省</option>-->
    <!--            <option value="安徽省">安徽省</option>-->
    <!--            <option value="福建省">福建省</option>-->
    <!--            <option value="江西省">江西省</option>-->
    <!--            <option value="山东省">山东省</option>-->
    <!--            <option value="河南省">河南省</option>-->
    <!--            <option value="湖北省">湖北省</option>-->
    <!--            <option value="湖南省">湖南省</option>-->
    <!--            <option value="广东省">广东省</option>-->
    <!--            <option value="广西壮族自治区">广西壮族自治区</option>-->
    <!--            <option value="海南省">海南省</option>-->
    <!--            <option value="重庆市">重庆市</option>-->
    <!--            <option value="四川省">四川省</option>-->
    <!--            <option value="贵州省">贵州省</option>-->
    <!--            <option value="云南省">云南省</option>-->
    <!--            <option value="西藏自治区">西藏自治区</option>-->
    <!--            <option value="陕西省">陕西省</option>-->
    <!--            <option value="甘肃省">甘肃省</option>-->
    <!--            <option value="青海省">青海省</option>-->
    <!--            <option value="宁夏回族自治区">宁夏回族自治区</option>-->
    <!--            <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>-->
    <!--            <option value="台湾省">台湾省</option>-->
    <!--            <option value="香港特别行政区">香港特别行政区</option>-->
    <!--            <option value="澳门特别行政区">澳门特别行政区</option>-->
    <!--          </select>-->
    <!--        </div>-->
    <!--        <div class="control col-12">-->
    <!--          <label class="form_lable" for="city">城市&ensp;</label>-->
    <!--          <select id="city" v-model="city" class="form-select form-control" name="city" @change="selectCity">-->
    <!--            <option selected>请选择省市</option>-->
    <!--          </select>-->
    <!--        </div>-->
    <!--        <div class="control col-12 ">-->
    <!--          <label class="form_lable" for="district">区/县&ensp;</label>-->
    <!--          <select id="district" v-model="district" class="form-select form-control" name="district"-->
    <!--                  @change="selectDistrict">-->
    <!--            <option selected>请选择城市</option>-->
    <!--          </select>-->
    <!--        </div>-->
    <!--        <div class="form col-12 ">-->
    <!--          <label class="form_lable" for="street">街道/乡镇</label>-->
    <!--          <select id="street" v-model="street" class="form-select form-control" name="street">-->
    <!--            <option selected>请选择乡镇</option>-->
    <!--          </select>-->
    <!--        </div>-->
    <!--        <div class="control col-12 ">-->
    <!--          <label class="form_lable" for="detailedAddress">详细地址</label>-->
    <!--          <input id="detailedAddress" v-model="detailedAddress" class="form-control" name="detailedAddress"-->
    <!--                 placeholder="详细地址" type="text"/>-->
    <!--        </div>-->
    <!--        <div class="control col-12">-->
    <!--          <input type="button" value="添加" @click="submit"/>-->
    <!--        </div>-->
    <!--      </div>-->
    <!--    </form>-->
  </div>
</template>

<script>
// eslint-disable-next-line camelcase
import Show_all_address from './show_all_address.vue'
import $ from 'jquery'

// const req = axios.create({
//   baseURL: 'http://10.90.246.218:8080/api/',
//   timeout: 1000,
//   withCredentials: true,
//   headers: {'X-Custom-Header': 'foobar', 'Access-Control-Allow-Origin': '*'}
// })
// import axios from "axios";
export default {
  name: '',

  components: {
    Show_all_address
  },
  data () {
    return {
      componentKey: 1,
      info: '',
      province: '',
      city: '',
      district: '',
      street: '',
      detailedAddress: ''
    }
  },
  methods: {
    selectProvince: function () {
      var select = $('#province')
      var nextSelect = $('#city')
      requestdistricts(select, nextSelect)
      // this.selectCity;
    },
    selectCity: function () {
      var select = $('#city')
      var nextSelect = $('#district')
      requestdistricts(select, nextSelect)
      // this.selectDistrict;
    },
    selectDistrict: function () {
      var select = $('#district')
      var nextSelect = $('#street')
      requestdistricts(select, nextSelect)
    },
    submit: function () {
      var patt1 = new RegExp('请选择')
      var province = $('#province')
      var city = $('#city')
      var district = $('#district')
      var street = $('#street')
      var address = $('#detailedAddress').val()
      if (patt1.test(province.val())) {
        alert('请选择省市')
      } else if (patt1.test(city.val())) {
        alert('请选择城市')
      } else if (patt1.test(district.val())) {
        alert('请选择区县')
      } else if (patt1.test(street.val())) {
        alert('请选择街道/乡镇')
      } else if (address == 'undefined' || !address || !/[^\s]/.test(address)) {
        alert('请填写详细地址!')
      } else {
        const item = localStorage.getItem('token')
        this.$axios.post('/addresss/add', {
          province: this.province,
          city: this.city,
          county: this.district,
          street: this.street,
          detailedAddress: this.detailedAddress
        }, {
          params: {
            token: item
          }
        }).then(resp => {
          if (resp.data.code != '-1') {
            alert(resp.data.message)
          } else {
            // var address = {
            //   rovince: this.province,
            //   city: this.city,
            //   county: this.district,
            //   street: this.street,
            //   detailedAddress: this.detailedAddress
            // }
            this.componentKey += 1
          }
        })
      }
    }
  }
}

function requestdistricts (select, nextSelect) {
  let option = select.val()

  var patt1 = new RegExp('请选择')
  console.log(!patt1.test(option))
  if (!patt1.test(option)) {
    $.ajax({
      url: 'https:///restapi.amap.com/v3/config/district?key=cfd5435365c43c5de7307cb3537ba58c&keywords=' + option + '&subdistrict=1&extensions=base',
      type: 'GET',
      dataType: 'json',
      success: function (data) {
        if (data.status == 1) {
          nextSelect.empty()
          nextSelect.append('<option>请选择</option>')
          $.each(data.districts[0].districts, function (i, item) {
            nextSelect.append('<option value=' + item.name + '>' + item.name + '</option>')
          })
        }
      },
      error: function (data) {
        alert('失败')
      }
    }
    )
  } else {
    nextSelect.empty()
    nextSelect.append('<option>请选择</option>')
  }
}

</script>
<style scoped>
.control label {
  text-align: center;
}

.form_lable {
  float: left;
}
</style>
