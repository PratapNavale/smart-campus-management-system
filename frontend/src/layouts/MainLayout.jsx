import { NavLink, Outlet } from "react-router-dom";

function MainLayout() {
  const menuClass = ({ isActive }) =>
    `block px-4 py-3 rounded-xl transition-all duration-200 ${
      isActive
        ? "bg-[#3A3A3A] text-white"
        : "text-gray-300 hover:bg-[#3A3A3A] hover:text-white"
    }`;

  return (
    <div className="min-h-screen flex bg-[#F5F5F5]">
      {/* Sidebar */}
      <aside className="w-[280px] bg-[#2B2B2B] text-white flex flex-col">
        {/* Logo */}
        <div className="h-[72px] flex items-center px-8 border-b border-[#3A3A3A]">
          <h1 className="text-3xl font-bold tracking-tight">
            ByteCampus
          </h1>
        </div>

        {/* Navigation */}
        <div className="flex-1 overflow-y-auto px-4 py-6">

          <div className="mb-8">
            <p className="text-xs uppercase text-gray-500 mb-3 px-4">
              Overview
            </p>

            <NavLink
              to="/admin/dashboard"
              className={menuClass}
            >
              Dashboard
            </NavLink>
          </div>

          <div className="mb-8">
            <p className="text-xs uppercase text-gray-500 mb-3 px-4">
              Academics
            </p>

            <div className="space-y-1">
              <NavLink to="/students" className={menuClass}>
                Students
              </NavLink>

              <NavLink to="/faculty" className={menuClass}>
                Faculty
              </NavLink>

              <NavLink to="/courses" className={menuClass}>
                Courses
              </NavLink>

              <NavLink to="/enrollments" className={menuClass}>
                Enrollments
              </NavLink>
            </div>
          </div>

          <div className="mb-8">
            <p className="text-xs uppercase text-gray-500 mb-3 px-4">
              Operations
            </p>

            <div className="space-y-1">
              <NavLink to="/attendance" className={menuClass}>
                Attendance
              </NavLink>

              <NavLink to="/hostel" className={menuClass}>
                Hostel
              </NavLink>

              <NavLink to="/payments" className={menuClass}>
                Payments
              </NavLink>
            </div>
          </div>

          <div>
            <p className="text-xs uppercase text-gray-500 mb-3 px-4">
              System
            </p>

            <NavLink to="/settings" className={menuClass}>
              Settings
            </NavLink>
          </div>
        </div>
      </aside>

      {/* Main Area */}
      <div className="flex-1 flex flex-col">

        {/* Top Navbar */}
        <header className="h-[72px] bg-white border-b border-[#D4D4D4] px-8 flex items-center justify-between">
          <div>
            <h2 className="text-xl font-semibold text-[#2B2B2B]">
              Administration Portal
            </h2>
          </div>

          <div className="flex items-center gap-4">
            <button className="px-4 py-2 border border-[#D4D4D4] rounded-xl hover:bg-[#F5F5F5] transition">
              Dark Mode
            </button>

            <div className="text-sm font-medium">
              Admin
            </div>
          </div>
        </header>

        {/* Content */}
        <main className="flex-1 p-6 overflow-y-auto">
          <Outlet />
        </main>
      </div>
    </div>
  );
}

export default MainLayout;