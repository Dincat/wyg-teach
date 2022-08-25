import request from '@/utils/request'

// 查询班级阶段列表
export function listClassStage(query) {
  return request({
    url: '/teach/classes/stage/list',
    method: 'get',
    params: query
  })
}

// 查询班级阶段详细
export function getClassStage(id) {
  return request({
    url: '/teach/classes/stage/' + id,
    method: 'get'
  })
}

// 新增班级阶段
export function addClassStage(data) {
  return request({
    url: '/teach/classes/stage',
    method: 'post',
    data: data
  })
}

// 修改班级阶段
export function updateClassStage(data) {
  return request({
    url: '/teach/classes/stage',
    method: 'put',
    data: data
  })
}

// 删除班级阶段
export function delClassStage(id) {
  return request({
    url: '/teach/classes/stage/' + id,
    method: 'delete'
  })
}
