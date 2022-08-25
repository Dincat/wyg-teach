
export const dialogStatusConstant = {
  create: 'create',
  update: 'update'
}

export const statusType = {
  0: 'success',
  1: 'warning'
}

// 考试类型
export const examType = {
  0: '正式考试',
  1: '模拟考试',
  2: '在线练习',
  3: '调查问卷'
}

// 题目类型
export const subjectType = {
  0: '单选题',
  2: '判断题',
  1: '简答题',
  3: '多选题'
}

export const subjectTypeTag = {
  0: 'success',
  1: 'info',
  2: 'warning',
  3: 'default'
}


export const userTypes={
  Administrator: {
    code:"00",
    msg:"管理员"
  },
  Teacher: {
    code:"11",
    msg:"教职师"
  },
  Student: {
    code:"33",
    msg:"职工"
  },
}

//班级状态
export const teachClassStatus = {
  Waiting: {
    code:"0",
    msg:"待开课"
  },
  Begin: {
    code:"1",
    msg:"待开课"
  },
  Over: {
    code:"2",
    msg:"待开课"
  },
}
