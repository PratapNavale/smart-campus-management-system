import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Eye } from "lucide-react";

import { login } from "../../api/authApi";

function LoginPage() {
  const navigate = useNavigate();

  const [role, setRole] = useState("Admin");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const [showPassword, setShowPassword] =
    useState(false);

  const [rememberMe, setRememberMe] =
    useState(false);

  const [loading, setLoading] =
    useState(false);

  const [error, setError] =
    useState("");

  useEffect(() => {
    const savedUsername =
      localStorage.getItem(
        "rememberedUsername"
      );

    if (savedUsername) {
      setUsername(savedUsername);
      setRememberMe(true);
    }
  }, []);

  const handleLogin = async (e) => {
    e.preventDefault();

    setError("");
    setLoading(true);

    try {
      const response = await login(
        username,
        password
      );

      localStorage.setItem(
        "token",
        response.token
      );

      if (rememberMe) {
        localStorage.setItem(
          "rememberedUsername",
          username
        );
      } else {
        localStorage.removeItem(
          "rememberedUsername"
        );
      }

      navigate("/admin/dashboard");
    } catch (err) {
      console.error(err);

      setError(
        "Invalid username or password"
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex">

      {/* Left Branding Section */}

      <div
        className="
        hidden
        lg:flex
        w-1/2
        bg-[#2B2B2B]
        text-white
        flex-col
        justify-center
        px-20
      "
      >
        <h1 className="text-7xl font-bold mb-6">
          ByteCampus
        </h1>

        <p className="text-2xl text-gray-300 mb-12">
          Campus Management System
        </p>

        <div className="space-y-5">
          <p className="text-3xl font-semibold">
            Administration Portal
          </p>

          <p className="text-3xl font-semibold">
            Faculty Portal
          </p>

          <p className="text-3xl font-semibold">
            Student Portal
          </p>
        </div>
      </div>

      {/* Right Login Section */}

      <div
        className="
        w-full
        lg:w-1/2
        flex
        items-center
        justify-center
        bg-[#F5F5F5]
        p-8
      "
      >
        <div
          className="
          w-full
          max-w-lg
          bg-white
          rounded-3xl
          shadow-xl
          p-12
          border
          border-gray-200
        "
        >
          <h2
            className="
            text-5xl
            font-bold
            text-[#2B2B2B]
            mb-3
          "
          >
            Welcome Back
          </h2>

          <p
            className="
            text-gray-500
            text-lg
            mb-8
          "
          >
            Sign in to continue to ByteCampus
          </p>

          <form onSubmit={handleLogin}>

            {/* Role */}

            <select
              value={role}
              onChange={(e) =>
                setRole(e.target.value)
              }
              className="
              w-full
              p-4
              rounded-xl
              border
              border-gray-300
              mb-4
              focus:outline-none
              focus:ring-2
              focus:ring-[#384959]
            "
            >
              <option>Admin</option>
              <option>Faculty</option>
              <option>Student</option>
            </select>

            {/* Username */}

            <input
              type="text"
              placeholder="Username"
              value={username}
              onChange={(e) =>
                setUsername(
                  e.target.value
                )
              }
              className="
              w-full
              p-4
              rounded-xl
              border
              border-gray-300
              mb-4
              focus:outline-none
              focus:ring-2
              focus:ring-[#384959]
            "
            />

            {/* Password */}

            <div className="relative mb-4">

              <input
                type={
                  showPassword
                    ? "text"
                    : "password"
                }
                placeholder="Password"
                value={password}
                onChange={(e) =>
                  setPassword(
                    e.target.value
                  )
                }
                className="
                w-full
                p-4
                pr-14
                rounded-xl
                border
                border-gray-300
                focus:outline-none
                focus:ring-2
                focus:ring-[#384959]
              "
              />

              <button
                type="button"
                className="
                absolute
                right-4
                top-1/2
                -translate-y-1/2
                text-gray-500
              "
                onMouseDown={() =>
                  setShowPassword(true)
                }
                onMouseUp={() =>
                  setShowPassword(false)
                }
                onMouseLeave={() =>
                  setShowPassword(false)
                }
              >
                <Eye size={22} />
              </button>

            </div>

            {/* Remember Me */}

            <div
              className="
              flex
              items-center
              mb-6
            "
            >
              <input
                type="checkbox"
                checked={rememberMe}
                onChange={(e) =>
                  setRememberMe(
                    e.target.checked
                  )
                }
                className="mr-3"
              />

              <span
                className="
                text-gray-700
              "
              >
                Remember Me
              </span>
            </div>

            {/* Error Message */}

            {error && (
              <div
                className="
                text-red-500
                mb-4
              "
              >
                {error}
              </div>
            )}

            {/* Login Button */}

            <button
              type="submit"
              disabled={loading}
              className="
              w-full
              bg-[#2B2B2B]
              text-white
              font-semibold
              p-4
              rounded-xl
              hover:bg-[#384959]
              transition-all
              duration-300
            "
            >
              {loading
                ? "Signing In..."
                : "Login"}
            </button>

          </form>

        </div>
      </div>

    </div>
  );
}

export default LoginPage;