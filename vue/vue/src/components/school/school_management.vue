<template>
  <div v-if="JSON.stringify(school)==='{}'" class="container">
    <span>你还不是学校管理员</span>
  </div>
  <div v-else>
    <div class="header navbar-fixed-top container-fluid">
      <div class="nav justify-content-center row-cols-4">
        <a class="navbar-brand col-4" href="/">TheGodIsGad</a>
        <div class="nav-item col-2">
          <button :class="{'bg-success text-light':navit===1}"
                  class="btn btn-outline-success"
                  type="button"
                  @click="navit=1">
            学校管理
          </button>
        </div>
        <div class="nav-item col-2">
          <button :class="{'bg-success text-light':navit===2}"
                  class="btn btn-outline-success"
                  type="button"
                  @click="navit=2">
            学院管理
          </button>
        </div>
        <div class="nav-item col-2">
          <button :class="{'bg-success text-light':navit>=3}"
                  class="btn btn-outline-success"
                  data-toggle="dropdown"
                  type="button"
                  @click="navit=3">
            学生管理
          </button>
        </div>
      </div>
    </div>
    <div v-if="navit===1" class="container">
      <div id="show_school_information">
        <table class="table">
          <thead>
          <tr class="row-cols-2">
            <th class="col-2"></th>
            <th class="col-10">{{ school.schoolName }}</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <th>学校代码</th>
            <td>
              <div class="input-group">
                <input v-model="schoolCode"
                       class="form-control border"
                       type="text"
                       v-bind:class="{'rounded-pill':schoolCodes>1,'border-success':schoolCodes==1,'border-warning':schoolCodes==3}"
                       v-bind:readonly="schoolCodes==1"
                       @dblclick="schoolCodes=2"
                       @input="schoolCodes=3">
                <div v-if="schoolCodes>=2">
                  <button :class="{'btn-outline-warning':schoolCodes==3}" :disabled="schoolCodes!=3" class="btn "
                          type="button"
                          @click="update_schoolCode">修改
                  </button>
                  <button class="btn btn-outline-secondary" type="reset"
                          @click="()=>{this.schoolCode=school.schoolCode;this.schoolCodes=1}"
                  >取消
                  </button>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>主管部门</th>
            <td>
              <div class="input-group">
                <input v-model="competentDepartment"
                       class="form-control border"
                       type="text"
                       v-bind:class="{'rounded-pill':competentDepartments>1,'border-success':competentDepartments==1,'border-warning':competentDepartments==3}"
                       v-bind:readonly="competentDepartments==1"
                       @dblclick="competentDepartments=2"
                       @input="competentDepartments=3">
                <div v-if="competentDepartments>=2">
                  <button :class="{'btn-outline-warning':competentDepartments==3}" :disabled="competentDepartments!=3"
                          class="btn " type="button"
                          @click="update_competentDepartment">修改
                  </button>
                  <button class="btn btn-outline-secondary" type="reset"
                          @click="()=>{
        this.competentDepartment=school.competentDepartment;this.competentDepartments=1
                        }"
                  >取消
                  </button>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>省市</th>
            <td>
              <div class="input-group">
                <input v-model="provincesCities"
                       class="form-control border"
                       type="text"
                       v-bind:class="{'rounded-pill':provincesCitiess>1,'border-success':provincesCitiess==1,'border-warning':provincesCitiess==3}"
                       v-bind:readonly="provincesCitiess==1"
                       @dblclick="provincesCitiess=2"
                       @input="provincesCitiess=3">
                <div v-if="provincesCitiess>=2">
                  <button :class="{'btn-outline-warning':provincesCitiess==3}" :disabled="provincesCitiess!=3"
                          class="btn " type="button"
                          @click="update_provincesCities">修改
                  </button>
                  <button class="btn btn-outline-secondary" type="reset"
                          @click="()=>{this.provincesCities=school.provincesCities;this.provincesCitiess=1}"
                  >取消
                  </button>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>所在地</th>
            <td>
              <div class="input-group">
                <input v-model="location"
                       class="form-control border"
                       type="text"
                       v-bind:class="{'rounded-pill':locations>1,'border-success':locations==1,'border-warning':locations==3}"
                       v-bind:readonly="locations==1"
                       @dblclick="locations=2"
                       @input="locations=3">
                <div v-if="locations>=2">
                  <button :class="{'btn-outline-warning':locations==3}" :disabled="locations!=3" class="btn "
                          type="button"
                          @click="update_location">修改
                  </button>
                  <button class="btn btn-outline-secondary" type="reset"
                          @click="()=>{this.location=school.location; this.locations=1}"
                  >取消
                  </button>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>办学层次</th>
            <td>
              <div class="input-group">
                <input v-model="educationLevel"
                       class="form-control border"
                       type="text"
                       v-bind:class="{'rounded-pill':educationLevels>1,'border-success':educationLevels==1,'border-warning':educationLevels==3}"
                       v-bind:readonly="educationLevels==1"
                       @dblclick="educationLevels=2"
                       @input="educationLevels=3">
                <div v-if="educationLevels>=2">
                  <button :class="{'btn-outline-warning':educationLevels==3}" :disabled="educationLevels!=3"
                          class="btn "
                          type="button"
                          @click="update_educationLevel">修改
                  </button>
                  <button class="btn btn-outline-secondary" type="reset"
                          @click="()=>{this.educationLevel=school.educationLevel;this.educationLevels=1}"
                  >取消
                  </button>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>简介</th>
            <td>
              <div class="input-group">
              <textarea v-model="note"
                        class="form-control border textarea"
                        oninput="textarea_auto_size(this)"
                        scrollHeight
                        type="text"
                        v-bind:class="{'border-success':notes==1,'border-warning':notes==3}"
                        v-bind:readonly="notes==1"
                        @dblclick="notes=2" @input="notes=3"></textarea>
                <div v-if="notes>=2">
                  <button :class="{'btn-outline-warning':notes==3}" :disabled="notes!=3" class="btn " type="button"
                          @click="update_node">修改
                  </button>
                  <button class="btn btn-outline-secondary" type="reset" @click="()=>{note=school.note;this.notes=1}"
                  >取消
                  </button>
                </div>
              </div>
            </td>
          </tr>

          </tbody>
        </table>
      </div>
      <div id="show_academy">

      </div>
    </div>
    <div v-else-if="navit===2" class="container">
      <div class="row ">
        <button v-for="(ac,index) in academys" :key="index" :class="academys_class[index]" class="btn col-3"

                @click="set_academy(ac)">{{
            ac.academyName
          }}
        </button>
        <button class="btn btn-outline-dark " style="margin-right: 5px;margin-top: 10px;"
                @click="()=>{this.academy={};this.add_academy=1;this.aca_note=''}">重置
        </button>
        <button class="btn btn-outline-success" style="margin-right: 5px;margin-top: 10px;"
                @click="()=>{this.academy={},this.add_academy=2}">添加学院
        </button>
      </div>
      <div v-if="JSON.stringify(academy)!='{}'" class="show_academy">
        <table class="table">
          <thead>
          <tr class="row-cols-2">
            <th class="col-2"></th>
            <th class="col-10">{{ academy.academyName }}</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(pro,index) in this.academy.professionals" :key="index">
            <th>{{ pro.professionalCode }}</th>
            <td>
              <div class="input-group">
                <span class="form-control">
              {{ pro.professionalName }}
                </span>
                <button class="btn border border-success" type="button"
                        @click="del_op(pro.professionalId,pro.professionalName)">删除
                </button>
              </div>
            </td>
          </tr>
          <tr>
            <th>添加专业</th>
            <td>
              <div>
                <input id="school" v-model="professionalId" class="form-select form-control" list="professionals"
                       name="professionals">
                <datalist id="professionals">
                  <option v-for="(pro,index) in all_professionals" :key="index" :value="pro.professionalId">{{
                      pro.professionalName
                    }}
                  </option>
                </datalist>
                <input class="form-control btn btn-outline-success" type="button" value="添加" @click="add_op">
              </div>
            </td>
          </tr>
          <tr>
            <th>简介</th>
            <td>
              <div class="input-group">
              <textarea v-model="aca_note"
                        class="form-control border textarea"
                        scrollHeight
                        type="text"
                        v-bind:class="{'border-success':aca_notes==1,'border-warning':aca_notes==3}"
                        v-bind:readonly="aca_notes==1"
                        @dblclick="aca_notes=2" @input="aca_notes=3"></textarea>
                <div v-if="aca_notes>=2">
                  <button :class="{'btn-outline-warning':aca_notes==3}" :disabled="aca_notes!=3" class="btn "
                          type="button"
                          @click="update_aca_note">修改
                  </button>
                  <button class="btn btn-outline-secondary" type="reset"
                          @click="()=>{this.aca_note=this.academy.note.note;this.aca_notes=1}"
                  >取消
                  </button>
                </div>
              </div>
            </td>
          </tr>
          <tr>
            <th>删除</th>
            <td>
              <input class="form-control btn btn-outline-danger" type="button" value="删除" @click="del_aca">
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div v-if="add_academy==2" class="add_academy">
        <table class="table">
          <thead>
          <thead>
          <tr class="row-cols-2">
            <th class="col-2"></th>
            <th class="col-10 text-center">添加学院</th>
          </tr>
          </thead>

          <tr>
            <th>学院名称</th>
            <td>
              <input v-model="add_aca_name" class="form-control border-success" placeholder="学院名称">
            </td>
          </tr>
          <tr>
            <th>学院简介</th>
            <td>
              <textarea v-model="add_aca_note" class="form-control border-success" cols="3"
                        defaultValue="学院简介"></textarea>
            </td>
          </tr>
          <tr>
            <th></th>
            <td><input :disabled="this.add_aca_name.length === 0" class="form-control btn border-success" type="button"
                       value="添加"
                       @click="add_aca"></td>
          </tr>
        </table>
      </div>
    </div>
    <div v-else class="container">
      <div class="row ">
        <button :class="{'bg-dark text-light':show_student==1}" class="btn btn-outline-dark col"
                @click="()=>{this.show_student=1}">查看学生
        </button>
        <button :class="{'bg-success text-light':show_student==2}" class="btn btn-outline-success col"
                @click="()=>{this.show_student=2}">添加学生
        </button>
      </div>
      <div v-if="show_student==1" class="show_student">
        <div v-if="JSON.stringify(students)=='[]'">
          <span>没有学生</span>
        </div>
        <div v-else>
          <table class="table">
            <thead>
            <tr>
              <th>学号</th>
              <th>学院</th>
              <th>专业</th>
              <th>年级</th>
              <th>姓名</th>
              <th>密码</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(stu,index) in students" :key="index">
              <td>{{ stu.studentId }}</td>
              <td>{{ stu.academyName }}</td>
              <td>{{ stu.professionalName }}</td>
              <td>{{ stu.grade }}</td>
              <td>{{ stu.nickName }}</td>
              <td>{{ stu.certificate }}</td>
              <td>
                <button class="btn btn-outline-warning" type="button" @click="delete_student(stu)">删除</button>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div v-else>
        <span>添加后可用学号+{{school.schoolPrefix}}登录</span>
        <table class="table text-left">
          <thead>
          <tr class="row-cols-6">
            <th class="col-2">学号</th>
            <th class="col-3">学院</th>
            <th class="col-3">专业</th>
            <th class="col-1">年级</th>
            <th class="col-1">姓名</th>
            <th class="col-1">密码</th>
            <th></th>
          </tr>
          </thead>
        </table>
        <tbody>
        <tr v-for="(stu_item,index) in this.add_students" :key="index" class="row-cols-6 ">
          <td class="col-2" style="padding-left: 0;padding-right: 1px">
            <input v-model="stu_item.studentId" class="form-control border-success" placeholder="学号" type="text">
          </td>
          <td class="col-3" style="padding-left: 0;padding-right: 1px">
            <select v-model="stu_item.academyId" class="form-control border-success"
                    @change="select_add_aca_item(stu_item)">
              <option v-for="(ac,index) in stu_item.academys" :key="index" :value="ac.academyId">{{ ac.academyName }}</option>
            </select>
          </td>
          <td class="col-3" style="padding-left: 0;padding-right: 1px">
            <select v-model="stu_item.professionalId" class="form-control border-success">
              <option :key="index" v-for="(ac,index) in stu_item.professionals" :value="ac.professionalId">{{ ac.professionalName }}</option>
            </select>
          </td>
          <td class="col-1" style="padding-left: 0;padding-right: 1px">
            <select v-model="stu_item.grade" class="form-select form-control">
              <option :key="index" v-for="(y,index) in year" :value="y">{{ y % 100 }}级</option>
            </select>
          </td>
          <td class="col-1" style="padding-left: 0;padding-right: 1px">
            <input v-model="stu_item.nickName" class="form-control border-success" placeholder="姓名" type="text">
          </td>
          <td class="col-1" style="padding-left: 0;padding-right: 1px">
            <input v-model="stu_item.certificate" class="form-control border-success" placeholder="密码" type="text">
          </td>
          <td><input class="form-control btn btn-outline-warning" type="button" value="删除"
                     @click="del_stu_itnem(index)">
          </td>
        </tr>
        </tbody>
        <input class="form-control btn btn-outline-success" type="button" value="添加学生" @click="add_stu_item">
        <input class="form-control btn btn-outline-success" type="button" value="提交" @click="add_student">
      </div>
    </div>
  </div>

</template>

<script>

export default {
  name: 'school_man',
  created () {
    this.get_school()

    this.get_allProfessional()
    var da = new Date()
    var toyear = da.getFullYear()
    var fif = toyear - 30
    for (; fif <= toyear; fif++) {
      this.year.push(fif)
    }
  },
  data: function () {
    return {
      year: [],
      navit: 1,
      note_rows: 1,
      school: {},
      schoolId: '',
      schoolName: '',
      schoolCode: '',
      competentDepartment: '',
      provincesCities: '',
      location: '',
      educationLevel: '',
      note: '',
      schoolNames: 1,
      schoolCodes: 1,
      competentDepartments: 1,
      provincesCitiess: 1,
      locations: 1,
      educationLevels: 1,
      notes: 1,
      colleges: [],
      update_school: {},
      academy: {},
      academys: [],
      academys_class: [],
      aca_note: '',
      aca_notes: 1,
      all_professionals: [],
      professionalId: null,
      add_academy: 1,
      add_aca_name: '',
      add_aca_note: '',
      students: [],
      add_students: [],
      show_student: 1
    }
  },
  watch: {
    note: function (no, ol) {
      if (no !== ol) {
        this.note_rows = no / 55
      }
    },
    academys () {
      this.radom()
    }
  },
  methods: {
    delete_student: function (stu) {
      var r = confirm('确定要删除' + stu.nickName + '吗')
      if (r) {
        this.$axios.post('/student/delete',
          stu,
          {
            params:
              {
                token: localStorage.getItem('token')
              }
          }
        ).then(response => {
          if (response.data.code === '-1') {
            this.students.forEach((value, index) => {
              if (value === stu) {
                this.students.splice(index, 1)
              }
            })
          }
          alert(response.data.message)
        })
      }
    },
    add_student: function () {
      this.$axios.post('/student/batch',
        this.add_students,
        {
          params: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
        var data = response.data
        if (data.code === '-1') {
          this.add_students = []
          this.get_alL_student()
          alert('添加成功')
        } else {
          alert(data.message)
        }
      })
    },
    select_add_aca_item: function (stu_item) {
      stu_item.academys.forEach((value) => {
        if (value.academyId == stu_item.academyId) {
          stu_item.professionals = value.professionals
        }
      })
    },
    del_stu_itnem: function (index) {
      this.add_students.splice(index, 1)
    },
    add_stu_item: function () {
      var stu_inem = {
        studentId: '',
        schoolId: this.schoolId,
        academyId: '',
        academys: this.academys,
        professionals: [],
        professionalId: '',
        grade: '',
        nickName: '',
        certificate: ''
      }
      this.add_students.push(stu_inem)
    },
    get_alL_student: function () {
      this.$axios.get('/student/get_all_by_school_id', {
        params: {
          token: localStorage.getItem('token'),
          schoolId: this.schoolId
        }
      }).then(response => {
        var data = response.data
        if (data.code === '-1') {
          if (data.result !== null) {
            if (JSON.stringify(this.students) != '[]') {
              this.students = []
            }
            this.students = data.result
            // console.log(this.students)
          }
        }
      })
    },
    radom: function () {
      this.academys_class = []
      if (this.academys != null) {
        for (var i = 0; i < this.academys.length; i++) {
          var num = Math.floor(Math.random() * 100) % 6
          switch (num) {
            case 0:
              this.academys_class.push('btn-outline-info  text-dark')
              break
            case 1:
              this.academys_class.push('btn-outline-primary  text-dark')
              break
            case 2:
              this.academys_class.push('btn-outline-secondary  text-dark')
              break
            case 3:
              this.academys_class.push('btn-outline-success  text-dark')
              break
            case 4:
              this.academys_class.push('btn-outline-warning  text-dark')
              break
            case 5:
              this.academys_class.push('btn-outline-danger  text-dark')
              break
          }
        }
      }
    },
    update_aca_note: function () {
      // alert(this.academy.academyId)
      this.$axios.post('academy/update', {
        academyId: this.academy.academyId,
        note: this.aca_note
      }, {params: {token: localStorage.getItem('token')}}).then(respone => {
        if (respone.data.code === '-1') {
          this.academy.note = this.aca_note
          this.aca_notes = 1
        }
      })
    },
    del_aca: async function () {
      var r = confirm('确定要删除' + this.academy.academyName + '吗')
      if (r == true) {
        await this.$axios.get('/academy/delete', {
          params: {
            token: localStorage.getItem('token'),
            academyId: this.academy.academyId
          }
        }).then(response => {
          if (response.data.code === '-1') {
            this.academys.forEach((value, index) => {
              if (value == this.academy) {
                this.academys.splice(index, 1)
                this.academy = {}
              }
            })
          }
        })
      }
    },
    add_aca: function () {
      this.$axios.post('/academy/add', {
        schoolId: this.schoolId,
        academyName: this.add_aca_name,
        note: this.add_aca_note
      }, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.code === '-1') {
          if (this.academys === null) {
            this.academys = []
          }
          this.academys.push(response.data.result)
          this.academy = response.data.result
          this.add_academy = 1
        } else {
          alert(response.data.message)
        }
      })
    },
    del_op: async function (pid, na) {
      var r = confirm('确定删除专业' + na + '吗!')
      if (r == true) {
        await this.$axios.post('/openingProfessional/delete', {
          academyId: this.academy.academyId,
          professionalId: pid
        }, {
          params: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
          if (response.data.code === '-1') {
            this.academy.professionals.forEach((value, index) => {
              if (value.professionalId == pid) {
                this.academy.professionals.splice(index, 1)
              }
            })
          } else {
            alert(response.data.message)
          }
        })
      }
    },
    add_op: async function () {
      var re = new RegExp('^\\d*$')
      if (re.test(this.professionalId)) {
        await this.$axios.post('/openingProfessional/add', {
          academyId: this.academy.academyId,
          professionalId: this.professionalId
        }, {
          params: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
          if (response.data.code === '-1') {
            this.all_professionals.forEach((value ) => {
              if (value.professionalId == this.professionalId) {
                if (this.academy.professionals === null) {
                  this.academy.professionals = []
                }
                this.academy.professionals.push(value)
              }
            })
          } else {
            alert(response.data.message)
          }
        })
      }
    },
    set_academy: function (ac) {
      this.academy = ac
      this.aca_note = ac.note
      this.add_academy = 1
    },
    get_academy: function () {
      this.$axios.get('/openingProfessional/school', {
        params: {
          token: localStorage.getItem('token'),
          schoolId: this.schoolId
        }
      }).then(response => {
        if (response.data.code === '-1') {
          this.academys = response.data.result
          //  console.log(this.academys)
        }
      })
    },
    get_allProfessional: function () {
      this.$axios.post('/professional/all', {}, {
        params:{
        token:localStorage.getItem('token')
        }
      }).then(response => {
        this.all_professionals = response.data.result
        // console.log(this.professionals)
      })
    },
    get_school: function () {
      this.$axios.get('/school/get', {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.code === '-1') {
          this.school = response.data.result
          this.schoolId = this.school.schoolId
          this.schoolName = this.school.schoolName
          this.schoolCode = this.school.schoolCode
          this.competentDepartment = this.school.competentDepartment
          this.provincesCities = this.school.provincesCities
          this.location = this.school.location
          this.educationLevel = this.school.educationLevel
          this.note = this.school.note
          this.get_academy()
          this.get_alL_student()
          // console.log(this.school)
        } else {
          alert(response.data.message)
        }
      })
    },
    update: async function (date) {
      date.schoolId = this.schoolId
      // console.log(date)
      var flag = false
      await this.$axios.post('school/update', date, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.code === '-1') {
          // console.log(response.data.code)
          flag = true
        }
      })
      return flag
    },
    update_node: function () {
      var scho = this.update_school
      scho.note = this.note
      var b = this.update(scho)
      if (b) {
        this.school.note = this.note
        this.notes = 1
      }
    },
    update_schoolCode: function () {
      var scho = this.update_school
      scho.schoolCode = this.schoolCode
      if (this.update(scho)) {
        this.school.schoolCode = this.schoolCode
        this.schoolCodes = 1
      }
    },
    update_competentDepartment: function () {
      var scho = this.update_school
      scho.competentDepartment = this.competentDepartment
      if (this.update(scho)) {
        this.school.competentDepartment = this.competentDepartment
        this.competentDepartments = 1
      }
    },
    update_provincesCities: function () {
      var scho = this.update_school
      scho.provincesCities = this.provincesCities
      if (this.update(scho)) {
        this.school.provincesCities = this.provincesCities
        this.provincesCitiess = 1
      }
    },
    update_location: function () {
      var scho = this.update_school
      scho.location = this.location
      if (this.update(scho)) {
        this.school.location = this.location
        this.locations = 1
      }
    },
    update_educationLevel: function () {
      var scho = this.update_school
      scho.educationLevel = this.educationLevel
      if (this.update(scho)) {
        this.school.educationLevel = this.educationLevel
        this.educationLevels = 1
      }
    }
  }
}

</script>

<style scoped>
</style>
