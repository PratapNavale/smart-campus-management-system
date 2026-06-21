import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

import AuthLayout from "../layouts/AuthLayout";
import MainLayout from "../layouts/MainLayout";

import LoginPage from "../pages/auth/LoginPage";
import AdminDashboard from "../pages/dashboard/AdminDashboard";

import ProtectedRoute from "./ProtectedRoute";

import StudentsPage from "../pages/students/StudentsPage";

function AppRoutes() {
  return (
    <BrowserRouter>

      <Routes>

        <Route
          path="/"
          element={
            <AuthLayout>
              <LoginPage />
            </AuthLayout>
          }
        />

        <Route
          path="/admin/dashboard"
          element={
            <ProtectedRoute>
              <MainLayout />
            </ProtectedRoute>
          }
          
        >
          <Route
            index
            element={<AdminDashboard />}
          />
        </Route>

        <Route
            path="/students"
            element={<StudentsPage />}
        />

      </Routes>

    </BrowserRouter>
  );
}

export default AppRoutes;