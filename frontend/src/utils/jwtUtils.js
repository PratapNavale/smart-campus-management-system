export const getToken = () => {
  return localStorage.getItem("token");
};

export const isAuthenticated = () => {
  return !!localStorage.getItem("token");
};

export const logout = () => {
  localStorage.removeItem("token");
};

export const parseJwt = (token) => {
  try {
    const base64Url = token.split(".")[1];

    const base64 = base64Url
      .replace(/-/g, "+")
      .replace(/_/g, "/");

    return JSON.parse(
      window.atob(base64)
    );
  } catch {
    return null;
  }
};

export const getCurrentUser = () => {
  const token =
    localStorage.getItem("token");

  if (!token) return null;

  return parseJwt(token);
};