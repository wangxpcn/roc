function post(url, data) {
    return axios({
        url: url,
        method: 'post',
        data: data
    })
}

function get(url, data) {
    return axios({
        url: url,
        method: 'get',
        data: data
    })
}