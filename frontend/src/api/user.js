import { API_BASE_URL, REDIRECT_URL } from "../config";

const KAKAO_AUTH_URL = `${API_BASE_URL}/oauth2/authorization/kakao?redirect_url=${REDIRECT_URL}`;

export { KAKAO_AUTH_URL };
