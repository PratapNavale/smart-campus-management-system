function LoginPage() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-[#384959]">
      <div className="w-full max-w-md bg-[#6A89A7] p-8 rounded-2xl shadow-xl">
        <h1 className="text-3xl font-bold text-white text-center mb-6">
          ByteCampus
        </h1>

        <p className="text-center text-white mb-6">
          Campus Management System
        </p>

        <select className="w-full p-3 rounded-lg mb-4">
          <option>Admin</option>
          <option>Faculty</option>
          <option>Student</option>
        </select>

        <input
          type="text"
          placeholder="Email"
          className="w-full p-3 rounded-lg mb-4"
        />

        <input
          type="password"
          placeholder="Password"
          className="w-full p-3 rounded-lg mb-6"
        />

        <button className="w-full bg-[#88BDF2] text-black font-semibold p-3 rounded-lg">
          Login
        </button>
      </div>
    </div>
  );
}

export default LoginPage;