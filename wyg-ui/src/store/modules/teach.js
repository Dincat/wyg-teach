import {getStudentByIdNumber} from "@/api/teach/student";
import {getTeacherByIdNumber} from "@/api/teach/teacher";

const teach = {
  state: {
    teacher: null,
    student: null,
    college: null,
    hasCollege: false
  },


  mutations: {
    SET_TEACHER: (state, teacher) => {
      state.teacher = teacher;
    },
    SET_STUDENT: (state, student) => {
      state.student = student;
    },
    SET_COLLEGE: (state, college) => {
      state.college = college;
      state.hasCollege = (college == null);
    }
  },

  actions: {
    GetTeacherInfo({ commit }, userInfo){
      const idNumber=userInfo.idNumber;

      return new Promise((resolve, reject) =>{

        getTeacherByIdNumber(idNumber).then(res=>{
          let data=res.data;
          commit('SET_TEACHER', data);

          if(data){
            commit('SET_COLLEGE', data.college);
          }

        });


      })

    },


    GetStudentInfo({ commit }, userInfo){
      const idNumber=userInfo.idNumber;

      return new Promise((resolve, reject) =>{

        getStudentByIdNumber(idNumber).then(res=>{
          let data=res.data;
          commit('SET_STUDENT', data);

          if(data){
            commit('SET_COLLEGE', data.college);
          }

        });


      })

    },


  }


}

export default teach
