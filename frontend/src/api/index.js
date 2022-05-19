import axios from "axios";
import { API_BASE_URL } from "../config";

const axiosService = axios.create({
  baseURL: API_BASE_URL,
});

function postRequest(url, data, token = undefined) {
  if (token === undefined) return axiosService.post(url, data);
  else
    return axiosService.post(url, data, {
      headers: { Authorization: `Bearer ${token}` },
    });
}

function getRequest(url, params = "", token = undefined) {
  if (params) {
    const keys = Object.keys(params);
    const values = Object.values(params);
    params =
      "?" +
      keys
        .map((key, i) => (values[i] === "" ? "" : `${key}=${values[i]}&`))
        .join("")
        .slice(0, -1);
  }
  if (token === undefined) return axiosService.get(url + params);
  else
    return axiosService.get(url + params, {
      headers: { Authorization: `Bearer ${token}` },
    });
}

function patchRequest(url, data, token = undefined) {
  if (token === undefined) return axiosService.patch(url, data);
  else
    return axiosService.patch(url, data, {
      headers: { Authorization: `Bearer ${token}` },
    });
}

function deleteRequest(url, data, token = undefined) {
  return axiosService.delete(url, data, {
    headers: { Authorization: `Bearer ${token}` },
  });
}
export { getRequest, postRequest, patchRequest, deleteRequest };
