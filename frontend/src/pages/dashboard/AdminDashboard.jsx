import { useEffect, useState } from "react";
import {
  getDashboardSummary,
  getActivityFeed,
} from "../../api/dashboardApi";

function AdminDashboard() {
  const [summary, setSummary] = useState(null);
  const [activities, setActivities] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadDashboard = async () => {
      try {
        const summaryData = await getDashboardSummary();
        const activityData = await getActivityFeed();

        setSummary(summaryData);
        setActivities(activityData);
      } catch (error) {
        console.error("Dashboard Load Error:", error);
      } finally {
        setLoading(false);
      }
    };

    loadDashboard();
  }, []);

  if (loading) {
    return (
      <div className="text-xl font-semibold">
        Loading Dashboard...
      </div>
    );
  }

  return (
    <div className="space-y-6">

      {/* Header */}
      <div>
        <h1 className="text-3xl font-bold text-[#2B2B2B]">
          Dashboard
        </h1>

        <p className="text-gray-500 mt-1">
          Campus overview and operational analytics.
        </p>
      </div>

      {/* KPI Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6">

        {/* Students */}
        <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6 shadow-sm hover:shadow-md transition">

          <p className="text-sm text-gray-500">
            Total Enrollment
          </p>

          <h2 className="text-3xl font-bold mt-2 text-[#2B2B2B]">
            {summary.totalStudents}
          </h2>

          <p className="text-sm text-gray-500 mt-2">
            Faculty: {summary.totalFaculty}
          </p>

        </div>

        {/* Attendance */}
        <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6 shadow-sm hover:shadow-md transition">

          <p className="text-sm text-gray-500">
            Today's Attendance
          </p>

          <h2 className="text-3xl font-bold mt-2 text-[#2B2B2B]">
            {summary.attendancePercentage}%
          </h2>

          <p className="text-sm text-green-700 mt-2">
            Present Today
          </p>

        </div>

        {/* Hostel */}
        <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6 shadow-sm hover:shadow-md transition">

          <p className="text-sm text-gray-500">
            Hostel Occupancy
          </p>

          <h2 className="text-3xl font-bold mt-2 text-[#D97706]">
            {summary.hostelOccupancyPercentage}%
          </h2>

          <p className="text-sm text-[#D97706] mt-2">
            {summary.vacantRooms} Rooms Available
          </p>

        </div>

        {/* Revenue */}
        <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6 shadow-sm hover:shadow-md transition">

          <p className="text-sm text-gray-500">
            Fees Collected
          </p>

          <h2 className="text-3xl font-bold mt-2 text-[#2B2B2B]">
            ₹{Number(summary.totalRevenue).toLocaleString("en-IN")}
          </h2>

          <p className="text-sm text-green-700 mt-2">
            Total Revenue
          </p>

        </div>

      </div>

      {/* Main Body */}
      <div className="grid grid-cols-1 xl:grid-cols-3 gap-6">

        {/* Left Side */}
        <div className="xl:col-span-2 space-y-6">

          {/* Chart Placeholder */}
          <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6">

            <h3 className="text-xl font-semibold text-[#2B2B2B]">
              Revenue & Fee Trends
            </h3>

            <div className="h-[320px] flex items-center justify-center text-gray-400">
              Chart Integration Coming Soon
            </div>

          </div>

          {/* Course Stats */}
          <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6">

            <h3 className="text-xl font-semibold text-[#2B2B2B]">
              Active Courses & Live Classes
            </h3>

            <div className="mt-6 grid grid-cols-2 gap-4">

              <div className="p-4 bg-[#F5F5F5] rounded-xl">

                <p className="text-sm text-gray-500">
                  Active Courses
                </p>

                <p className="text-2xl font-bold">
                  {summary.totalCourses}
                </p>

              </div>

              <div className="p-4 bg-[#F5F5F5] rounded-xl">

                <p className="text-sm text-gray-500">
                  Total Enrollments
                </p>

                <p className="text-2xl font-bold">
                  {summary.totalEnrollments}
                </p>

              </div>

            </div>

          </div>

        </div>

        {/* Right Side */}
        <div className="space-y-6">

          {/* Activity Feed */}
          <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6">

            <h3 className="text-xl font-semibold text-[#2B2B2B] mb-4">
              Recent Activity
            </h3>

            <div className="space-y-4">

              {activities.map((activity, index) => (
                <div
                  key={index}
                  className="border-l-2 border-[#D4D4D4] pl-3"
                >
                  <p className="font-medium">
                    {activity.description}
                  </p>

                  <p className="text-xs text-gray-500 mt-1">
                    {activity.createdAt}
                  </p>
                </div>
              ))}

            </div>

          </div>

          {/* Quick Actions */}
          <div className="bg-white rounded-2xl border border-[#D4D4D4] p-6">

            <h3 className="text-xl font-semibold text-[#2B2B2B] mb-4">
              Quick Actions
            </h3>

            <div className="space-y-3">

              <button className="w-full p-3 rounded-xl bg-[#2B2B2B] text-white hover:bg-[#3A3A3A] transition">
                Add Faculty
              </button>

              <button className="w-full p-3 rounded-xl bg-[#2B2B2B] text-white hover:bg-[#3A3A3A] transition">
                Create Course
              </button>

              <button className="w-full p-3 rounded-xl bg-[#2B2B2B] text-white hover:bg-[#3A3A3A] transition">
                Issue Notice
              </button>

            </div>

          </div>

        </div>

      </div>

    </div>
  );
}

export default AdminDashboard;