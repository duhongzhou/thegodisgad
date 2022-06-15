<template>
  <table class="table">
    <thead>
    <tr class="row-cols-2">
      <th class="col-2">教育经历</th>
      <th class="col-10"></th>
    </tr>

    </thead>
    <tbody>
    <tr>
      <th>学校</th>
      <td>
        <div v-if="!student_is_empty()" class="form-control text-left">
          <span>{{ schoolName }}</span>
        </div>
        <div v-else>
          <input id="school" v-model="schoolId" class="form-select form-control" list="schools"
                 name="school" @change="select_school()">
          <datalist id="schools">
            <option :key="index" v-for="(school,index) in schools" :style="{'color:red':school.schoolId%2==1}" :value="school.schoolId">
              {{ school.schoolName }}
            </option>
          </datalist>
        </div>
      </td>
    </tr>
    <tr>
      <th>专业</th>
      <td>
        <div v-if="!student_is_empty()" class="form-control text-left">
          <span>{{ professionalName }}</span>
        </div>
        <div v-else>
          <input id="school" v-model="professionalId" class="form-select form-control" list="professionals"
                 name="professionals">
          <datalist id="professionals">
            <option v-for="(pro,index) in professionals" :key="index" :value="pro.professionalId">{{ pro.professionalName }}</option>
          </datalist>
        </div>
      </td>
    </tr>
    <tr>
      <th>年级</th>
      <td>
        <div v-if="!student_is_empty()" class="form-control text-left">
          <span>{{ student.grade }}</span>
        </div>
        <div v-else>
          <select v-model="grad" class="form-select form-control">
            <option v-for="(y,index) in year" :value="y" :key="index">{{ y % 100 }}级</option>
          </select>
        </div>
      </td>
    </tr>
    <tr v-if="student_is_empty()">
      <th></th>
      <td>
        <div>
          <button :disabled="(schoolId==''&&professionalId=='')" class="form-control" @click="add_student()">添加</button>
        </div>
      </td>
    </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: 'show_user_school',
  created () {
    // console.log((JSON.stringify(this.student) == '{}') + 'test')
    var da = new Date()
    var toyear = da.getFullYear()
    var fif = toyear - 30
    for (; fif <= toyear; fif++) {
      this.year.push(fif)
    }
    this.get_student()
  },
  data: function () {
    return {
      student: {},
      schools: [],
      schoolId: '',
      schoolName: '',
      professionals: [],
      professionalId: '',
      professionalName: '',
      professional: '',
      grad: null,
      year: []
    }
  },
  methods: {
    student_is_empty: function () {
      const b = JSON.stringify(this.student) == '{}'
      return b
    },
    get_school_name: function () {
      this.$axios.get('/school/get_by_school_id', {
        params: {
          token: localStorage.getItem('token'),
          schoolId: this.student.schoolId
        }
      }).then(response => {
        let data = response.data
        this.schoolName = data.result.schoolName
      })
    },
    get_professional_name: function () {
      this.$axios.get('/professional/get_by_professional_id', {
        params: {
          token: localStorage.getItem('token'),
          professionalId: this.student.professionalId
        }
      }).then(response => {
        let data = response.data
        this.professionalName = data.result.professionalName
      })
    },
    get_student: function () {
      this.$axios.post('/student/show', {}, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.code == '-1') {
          this.student = response.data.result
          this.get_school_name()
          this.get_professional_name()
        } else {
          this.get_allSchool()
          this.get_allProfessional()
        }
      })
    },
    get_allSchool: function () {
      this.$axios.post('/school/all', {}, {
        params:{
          token:localStorage.getItem('token')
        }
      }).then(response => {
        this.schools = response.data.result
      })
    },
    get_allProfessional: function () {
      this.$axios.post('/professional/all', {}, {
        params:{
          token:localStorage.getItem('token')
        }
      }).then(response => {
        this.professionals = response.data.result
        // console.log(this.professionals)
      })
    },
    add_student: function () {
      this.$axios.post('/student/add', {
        schoolId: this.schoolId,
        grade: this.grad,
        professionalId: this.professionalId
      }, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        this.student = response.data.result
      })
    }
  }
}
</script>

<style scoped>

</style>
