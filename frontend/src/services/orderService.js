import axios from 'axios';

// 주문 삽입
export const addOrder = (orderData) => {
    return axios.post('/v1/api/orders',orderData).catch(e => e.response);
}

// 주문 목록 조회
export const getOrder = () => {
    return axios.get("/v1/api/orders").catch(e => e.response);
}
// 주문 상세 조회
