import {fileURLToPath, URL} from 'node:url'
import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        // server.proxy : 프록시 설정 부분
        proxy: {
            "/v1/api": {
                target: "http://localhost:8080",
                changeOrigin: true,
                // rewrite: (path) => path.replace(/^\/api/,''),
            }
        }

    },


    plugins: [
        vue(),
    ],
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    }
})


// 3. CORS(Cross-Origin Resouce Sharing)
// 클라이언트와 서버의 URL  http://localhost  포트가(5173,8080) 서로 다르기 때문에
//     CORS(Cross-Origin Resouce Sharing) 문제가 발생함.
//     서버 객체에 proxy를 추가한다.
// 장점 : 프론트와 백을 독립적으로 구성하고 경로에 대한 충돌 없이 연결할 수 있다.
// 1. CORS 에러 방지
// 2. 실제 백엔드 주소를 숨기고 개발용 주소로 통일할 수 있음
// 3. 동일 출처 정책 우회
// 4. 코드 배포 전까지 프론트와 백을 완전히 분리해서 구현할 수 있다.