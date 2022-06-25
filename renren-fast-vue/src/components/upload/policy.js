import http from '@/utils/httpRequest.js'
export function policy(filename) {
  console.log(filename)
  return new Promise((resolve, reject) => {
    http({
      url: http.adornUrl("/thirdparty/s3/policy/" + filename),
      method: "get",
      params: http.adornParams({})
    }).then(({
      data
    }) => {
      resolve(data);
    })
  });
}
