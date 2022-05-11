import { createInstance } from "./index.js";
import { API_BASE_URL } from "../config";

const instance = createInstance();

function signup(success, fail) {
  window.open(API_BASE_URL + "/oauth2/authorization/kakao");
}
export { signup };
