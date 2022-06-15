<template :key="key">
  <div>
    <div>
      <table class="table table-bordere  table-condensed"
             style="word-break:break-all;">
        <thead>
        <tr class="row-cols-2">
          <th class="col-2">地址管理</th>
        </tr>
        <tr>
          <th>省市</th>
          <th>城市</th>
          <th>区县</th>
          <th>街道</th>
          <th>详细地址</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>

        <tr v-for="(add,index) in address" :key="index">
          <td>{{ add.province }}</td>
          <td>{{ add.city }}</td>
          <td>{{ add.county }}</td>
          <td>{{ add.street }}</td>
          <td>{{ add.detailedAddress }}</td>
          <td>
            <button class="button" type="button" @click="del(index)">删除</button>
          </td>
        </tr>
        <tr>
          <td>
            <div class="control ">
              <select id="province" v-model="province" class="form-select form-control" name="province"
                      @change="selectProvince">
                <option selected>请选择</option>
                <option value="北京市">北京市</option>
                <option value="天津市">天津市</option>
                <option value="河北省">河北省</option>
                <option value="山西省">山西省</option>
                <option value="内蒙古自治区">内蒙古自治区</option>
                <option value="辽宁省">辽宁省</option>
                <option value="吉林省">吉林省</option>
                <option value="黑龙江省">黑龙江省</option>
                <option value="上海市">上海市</option>
                <option value="江苏省">江苏省</option>
                <option value="浙江省">浙江省</option>
                <option value="安徽省">安徽省</option>
                <option value="福建省">福建省</option>
                <option value="江西省">江西省</option>
                <option value="山东省">山东省</option>
                <option value="河南省">河南省</option>
                <option value="湖北省">湖北省</option>
                <option value="湖南省">湖南省</option>
                <option value="广东省">广东省</option>
                <option value="广西壮族自治区">广西壮族自治区</option>
                <option value="海南省">海南省</option>
                <option value="重庆市">重庆市</option>
                <option value="四川省">四川省</option>
                <option value="贵州省">贵州省</option>
                <option value="云南省">云南省</option>
                <option value="西藏自治区">西藏自治区</option>
                <option value="陕西省">陕西省</option>
                <option value="甘肃省">甘肃省</option>
                <option value="青海省">青海省</option>
                <option value="宁夏回族自治区">宁夏回族自治区</option>
                <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
                <option value="台湾省">台湾省</option>
                <option value="香港特别行政区">香港特别行政区</option>
                <option value="澳门特别行政区">澳门特别行政区</option>
              </select>
            </div>
          </td>
          <td>
            <div class="control ">
              <select id="city" v-model="city" class="form-select form-control" name="city" @change="selectCity">
                <option selected>请选择省市</option>
              </select>
            </div>
          </td>
          <td>
            <div class="control  ">
              <select id="district" v-model="district" class="form-select form-control" name="district"
                      @change="selectDistrict">
                <option selected>请选择城市</option>
              </select>
            </div>
          </td>
          <td>
            <div class="form  ">
              <select id="street" v-model="street" class="form-select form-control" name="street">
                <option selected>请选择乡镇</option>
              </select>
            </div>
          </td>
          <td>
            <div class="control  ">
              <input id="detailedAddress" v-model="detailedAddress" class="form-control" name="detailedAddress"
                     placeholder="详细地址" type="text"/>
            </div>
          </td>
          <td>
            <div class="control ">
              <input type="button" value="添加" @click="submit"/>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

  </div>

</template>

<script>

import $ from 'jquery'

export default {
  name: 'show_all_address',

  data () {
    return {
      key: 1,
      address: [],
      info: '',
      province: '',
      city: '',
      district: '',
      street: '',
      detailedAddress: ''

    }
  },
  created () {
    this.get()
  },
  methods: {
    get: function () {
      var item = localStorage.getItem('token')
      this.$axios.post('/addresss/search?token=' + item).then(response => {
        var data = response.data
        if (data.code == '-1') {
          this.address = data.result
          // alert(data.message)
        }
      })
    },
    del: function (index) {
      var item = localStorage.getItem('token')

      this.$axios.post('/addresss/delete', {
        userId: this.address[index].userId,
        province: this.address[index].province,
        city: this.address[index].city,
        county: this.address[index].county,
        street: this.address[index].street,
        detailedAddress: this.address[index].detailedAddress
      }, {
        params: {
          token: item
        }
      }).then(response => {
        var data = response.data
        console.log(data)
        if (data.code == '-1') {
          this.address.splice(index, 1)
          this.key += 1
        }
      })
    },
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
            // eslint-disable-next-line no-undef
            this.address.push(resp.data.result)
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
          var length = Object.keys(data.districts[0].districts).length
          console.log(length)
          if (length != 0) {
            $.each(data.districts[0].districts, function (i, item) {
              nextSelect.append('<option value=' + item.name + '>' + item.name + '</option>')
            })
          } else {
            nextSelect.append('<option value="选我">选我</option>')
          }
        }
      },
      error: function (data) {
        alert('失败'+data)
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

</style>
