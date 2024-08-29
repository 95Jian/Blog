import axios from '@/plugins/axios'

export function getBlogById(token, id) {
    return axios({
        url: 'blog',
        method: 'GET',
        headers: {
            Authorization: token,
        },
        params: {
            id
        }
    })
}



export function getSearchBlogList(query) {
    return axios({
        url: 'searchBlog',
        method: 'GET',
        params: {
            query
        }
    })
}